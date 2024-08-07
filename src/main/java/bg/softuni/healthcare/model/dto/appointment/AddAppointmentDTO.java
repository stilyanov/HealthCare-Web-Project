package bg.softuni.healthcare.model.dto.appointment;

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
public class AddAppointmentDTO {
    private Long id;

    private LocalDateTime dateTime;

    @NotNull(message = "Reason cannot be empty!")
    @Size(min = 10, max = 500, message = "Reason length must be between 10 and 500 characters!")
    private String reason;

    private DepartmentEnum department;

    private Long doctorId;
    private Long patientId;
}
