package bg.softuni.healthcare.model.dto.appointment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class FullAppointmentsInfoDTO {

    private Long id;
    private Long patientId;
    private Long doctorId;
    private String reason;
    private LocalDateTime dateTime;
    private String patientFullName;
    private String doctorFullName;
    private String formattedDateTime;

}
