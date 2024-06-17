package in.clear.EmployeeTask.repository;

import in.clear.EmployeeTask.Dto.DepartmentCountDto;

import java.util.List;
import java.util.Optional;

public interface EmployeeCustomRepository {
    public List<DepartmentCountDto> findDepartmentCount();
    Optional<Double> getAverageExperience();
}
