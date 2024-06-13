package in.clear.EmployeeTask.Dto;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Data
@NoArgsConstructor
@Component
public class SuccessResponseModel {
    private String id;
    private String message;
}
