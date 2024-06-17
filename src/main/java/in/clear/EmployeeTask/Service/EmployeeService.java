package in.clear.EmployeeTask.Service;


import in.clear.EmployeeTask.Dto.DepartmentCountDto;
import in.clear.EmployeeTask.Dto.EmployeeModelDto;
import in.clear.EmployeeTask.Dto.SuccessResponseModel;
import in.clear.EmployeeTask.Model.EmployeeModel;

import java.util.List;

public interface EmployeeService {
    public SuccessResponseModel Upload(EmployeeModel Employee);
    public EmployeeModelDto getEmployee(String id);
    public SuccessResponseModel updateEmployee(String id,EmployeeModel Employee);

    public SuccessResponseModel deleteEmployee(String id);
    public double getAverageExperience();
    public List<DepartmentCountDto> getDepartmentCount();
}
