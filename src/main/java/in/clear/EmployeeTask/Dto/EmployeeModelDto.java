package in.clear.EmployeeTask.Dto;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Builder
@Data
@Document
public class EmployeeModelDto {
    private String id;
    private String name;
    private String email;
    private String department;
    private String designation;
    private Date joinDate;
}
