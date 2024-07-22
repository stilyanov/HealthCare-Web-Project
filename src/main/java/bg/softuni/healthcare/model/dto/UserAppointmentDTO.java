package bg.softuni.healthcare.model.dto;

import bg.softuni.healthcare.model.entity.enums.DepartmentEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
public class UserAppointmentDTO {

    private String doctorFullName;
    private DepartmentEnum department;
    private String time;
    private String reason;
}
