package bg.softuni.healthcare.model.dto;

import bg.softuni.healthcare.model.entity.enums.DepartmentEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AppointmentDTO {
    private Long id;

    @NotNull(message = "Time cannot be empty!")
    private LocalDateTime time;

    @NotNull(message = "Reason cannot be empty!")
    @Size(min = 10, max = 200, message = "Reason length must be between 10 and 200 characters!")
    private String reason;

    private Long doctorId;
    private Long patientId;
}
