package in.clear.EmployeeTask.Service.Impl;

import in.clear.EmployeeTask.Dto.DepartmentCountDto;
import in.clear.EmployeeTask.Dto.EmployeeModelDto;
import in.clear.EmployeeTask.Dto.SuccessResponseModel;
import in.clear.EmployeeTask.Model.EmployeeModel;
import in.clear.EmployeeTask.Service.EmployeeService;
import in.clear.EmployeeTask.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    @Override
    public SuccessResponseModel Upload(EmployeeModel employee){
        try{
            employeeRepository.insert(employee);
            log.info("here i am ");
            SuccessResponseModel successResponseModel=new SuccessResponseModel();
            successResponseModel.setId(employee.getUserId());
            successResponseModel.setMessage("Employee Created Successfully");
            return successResponseModel;
        }
        catch(Exception e){
            log.info("Employee error :{}", e.getMessage());
            SuccessResponseModel successResponseModel=new SuccessResponseModel();
            successResponseModel.setId(employee.getUserId());
            successResponseModel.setMessage("Error in creating Employee");
            return successResponseModel;
        }
    }
    @Override
    public EmployeeModelDto getEmployee(String id){
        try{
        EmployeeModel employee = employeeRepository.findByUserId(id);
        if (employee == null) {
            throw new RuntimeException("Employee not found with ID: " + id);
        }

        EmployeeModelDto employeeResponse = EmployeeModelDto.builder()
                .id(employee.getUserId())
                .name(employee.getName())
                .email(employee.getEmail())
                .designation(employee.getDesignation())
                .department(employee.getDepartment())
                .joinDate(employee.getJoinDate())
                .build();

        return employeeResponse;
    } catch(RuntimeException e) {
            // Handle the exception here, you can log it or throw a different exception
            log.info("Error:{}",e.getMessage()); // Example of logging the exception
            return null;
            // Return a default or error response
        }

    }

    @Override
    public SuccessResponseModel updateEmployee(String id, EmployeeModel employee){

            EmployeeModel existingEmployee = employeeRepository.findByUserId(id);
        // Update name
        if (employee.getName() != null) {
            existingEmployee.setName(employee.getName());
        }
        // Update email
        if (employee.getEmail() != null) {
            existingEmployee.setEmail(employee.getEmail());
        }
        // Update department
        if (employee.getDepartment() != null) {
            existingEmployee.setDepartment(employee.getDepartment());
        }
        // Update designation
        if (employee.getDesignation() != null) {
            existingEmployee.setDesignation(employee.getDesignation());
        }
        // Update join date
        if (employee.getJoinDate() != null) {
            existingEmployee.setJoinDate(employee.getJoinDate());
        }
        employeeRepository.save(existingEmployee);
        SuccessResponseModel successResponseModel=new SuccessResponseModel();
            successResponseModel.setMessage("Employee Updated Successfully");
            return successResponseModel;

    }

    @Override
    public SuccessResponseModel deleteEmployee(String id){
        employeeRepository.deleteById(id);
        SuccessResponseModel successResponseModel=new SuccessResponseModel();
        successResponseModel.setId(id);
        successResponseModel.setMessage("Employee Deleted Successfully");
        return successResponseModel;
    }
    @Override
    public List<DepartmentCountDto> getDepartmentCount(){
        return employeeRepository.findDepartmentCount();
    }
    @Override
    public double getAverageExperience() {
        return employeeRepository.getAverageExperience().orElse(0.0);
    }

}

