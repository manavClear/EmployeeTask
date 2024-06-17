package in.clear.EmployeeTask.repository;

import in.clear.EmployeeTask.Dto.DepartmentCountDto;
import in.clear.EmployeeTask.Model.EmployeeModel;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class EmployeeCustomRepositoryImpl implements EmployeeCustomRepository{
    private final MongoTemplate mongoTemplate;
    @Override
    public List<DepartmentCountDto> findDepartmentCount(){
        GroupOperation groupOperation = Aggregation.group("department","designation").count().as("employeeCount");
        ProjectionOperation projectionOperation = Aggregation.project("department","designation").andInclude("employeeCount");
        Aggregation aggregation = Aggregation.newAggregation(groupOperation,projectionOperation);
        return mongoTemplate.aggregate(aggregation, EmployeeModel.class,DepartmentCountDto.class).getMappedResults();
    }
    @Override
    public Optional<Double> getAverageExperience(){
        /*LocalDate now = LocalDate.now();

        Document currentDate = new Document("$dateFromString", new Document("dateString", now.toString()));

        Document projection = new Document("$project", new Document("yearsOfExperience",
                new Document("$divide", Arrays.asList(
                        new Document("$subtract", Arrays.asList(currentDate, "$joinDate")),
                        1000 * 60 * 60 * 24 * 365.25
                ))
        ));

        Document group = new Document("$group", new Document("_id", null)
                .append("averageExperience", new Document("$avg", "$yearsOfExperience")));

        Document result = mongoTemplate.getCollection("employeeModel")
                .aggregate(Arrays.asList(projection, group))
                .first();

        if (result != null) {
            return Optional.ofNullable(result.getDouble("averageExperience"));
        } else {
            return Optional.empty();
        }*/

        LocalDate now = LocalDate.now();
        long nowMillis = java.sql.Date.valueOf(now).getTime();

        ProjectionOperation projectionOperation = Aggregation.project()
                .andExpression("{$divide: [{$subtract: [" + nowMillis + ", {$toLong: '$joinDate'}]}, " + (1000 * 60 * 60 * 24 * 365.25) + "]}")
                .as("yearsOfExperience");

        GroupOperation groupOperation = Aggregation.group()
                .avg("yearsOfExperience")
                .as("averageExperience");

        Aggregation aggregation = Aggregation.newAggregation(projectionOperation, groupOperation);

        AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, "employeeModel", Document.class);

        Document result = results.getUniqueMappedResult();

        if (result != null) {
            return Optional.ofNullable(result.getDouble("averageExperience"));
        } else {
            return Optional.empty();
        }
    }
}
