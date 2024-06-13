package in.clear.EmployeeTask.Model;

import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Data
public class EmployeeModel {
    @Id
    @Field("id")
    private String userId;
    private String name;
    private String email;
    private String department;
    private String designation;
    private Date joinDate;
    public EmployeeModel(){
        this.userId = UUID.randomUUID().toString();
    }
}
