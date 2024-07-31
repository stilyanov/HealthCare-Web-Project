package bg.softuni.healthcare.model.dto;

import bg.softuni.healthcare.model.entity.enums.DepartmentEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class DoctorAppointmentDTO {

    private Long patientId;
    private DepartmentEnum department;
    private LocalDateTime dateTime;
    private String reason;
    private String patientFullName;
    private String formattedDateTime;

}
