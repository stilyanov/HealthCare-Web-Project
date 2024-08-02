package bg.softuni.healthcare.model.dto.user;

import bg.softuni.healthcare.model.entity.enums.DepartmentEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserAppointmentDTO {

    private Long id;
    private Long doctorId;
    private DepartmentEnum department;
    private LocalDateTime dateTime;
    private String reason;
    private String doctorFullName;
    private String formattedDateTime;
}
