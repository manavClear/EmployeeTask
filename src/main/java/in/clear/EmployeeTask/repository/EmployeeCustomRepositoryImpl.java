package in.clear.EmployeeTask.repository;

import in.clear.EmployeeTask.Dto.DepartmentCountDto;
import in.clear.EmployeeTask.Model.EmployeeModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;

import java.util.List;

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

}
