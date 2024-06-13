package in.clear.EmployeeTask.repository;

import in.clear.EmployeeTask.Model.EmployeeModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<EmployeeModel,String>,EmployeeCustomRepository {

    EmployeeModel findByUserId(String id);
}
