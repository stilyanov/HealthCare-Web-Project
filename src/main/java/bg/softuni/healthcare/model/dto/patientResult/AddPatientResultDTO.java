package bg.softuni.healthcare.model.dto.patientResult;

import bg.softuni.healthcare.model.entity.UserEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class AddPatientResultDTO {

    @NotNull(message = "Prescription cannot be null")
    @Size(min = 10, max = 500, message = "Prescription length must be between 10 and 500 characters!")
    private String prescription;

    @NotNull(message = "Result cannot be null")
    @Size(min = 10, max = 1000, message = "Result length must be between 10 and 1000 characters!")
    private String result;

    @NotNull(message = "Date and Time cannot be null")
    private LocalDate date;

    @NotNull(message = "Patient ID cannot be null")
    private Long patientId;

    private Long appointmentId;
}
