package bg.softuni.healthcare.model.dto.patientResult;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class PatientResultDTO {
    private Long id;
    private String prescription;
    private String result;
    private LocalDate date;
    private Long appointmentId;
}
