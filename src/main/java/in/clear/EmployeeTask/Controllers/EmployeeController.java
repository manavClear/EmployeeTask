package in.clear.EmployeeTask.Controllers;


import in.clear.EmployeeTask.Dto.DepartmentCountDto;
import in.clear.EmployeeTask.Dto.EmployeeModelDto;
import in.clear.EmployeeTask.Dto.SuccessResponseModel;
import in.clear.EmployeeTask.Model.EmployeeModel;
import in.clear.EmployeeTask.Service.EmployeeService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@Data
@RestController
@RequestMapping("api")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/employees")
    public SuccessResponseModel Upload(@RequestBody EmployeeModel employee) {
        return employeeService.Upload(employee);
    }

    @GetMapping("/employee/{id}")
    public EmployeeModelDto getEmployee(@PathVariable String id) {
        return employeeService.getEmployee(id);
    }

    @PutMapping("/employee/{id}")
    public SuccessResponseModel updateEmployee(@PathVariable String id, @RequestBody EmployeeModel employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/employee/{id}")
    public SuccessResponseModel deleteEmployee(@PathVariable String id) {
        return employeeService.deleteEmployee(id);
    }

    @GetMapping("/employees/department-count")
    public List<DepartmentCountDto> getDepartmentCount() {
        return employeeService.getDepartmentCount();
    }
    @GetMapping("/employees/average-experience")
    public Map<String, Double> getAverageExperience() {
        double averageExperience = employeeService.getAverageExperience();
        return Map.of("averageExperience", averageExperience);
    }
}
