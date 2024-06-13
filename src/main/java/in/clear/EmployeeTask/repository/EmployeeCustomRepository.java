package in.clear.EmployeeTask.repository;

import in.clear.EmployeeTask.Dto.DepartmentCountDto;

import java.util.List;

public interface EmployeeCustomRepository {
    public List<DepartmentCountDto> findDepartmentCount();

}
