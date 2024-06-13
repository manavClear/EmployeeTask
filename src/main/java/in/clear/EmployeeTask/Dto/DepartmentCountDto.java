package in.clear.EmployeeTask.Dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepartmentCountDto {
    private String department;
    private String designation;
    private int employeeCount;
}
