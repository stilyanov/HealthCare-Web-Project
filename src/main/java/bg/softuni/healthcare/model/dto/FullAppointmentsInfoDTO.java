package bg.softuni.healthcare.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FullAppointmentsInfoDTO {

    private Long id;
    private String patientFullName;
    private String doctorFullName;
    private String reason;
    private String time;

}
