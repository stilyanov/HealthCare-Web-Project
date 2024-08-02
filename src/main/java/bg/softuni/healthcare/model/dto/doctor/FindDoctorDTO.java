package bg.softuni.healthcare.model.dto.doctor;

import bg.softuni.healthcare.model.entity.enums.DepartmentEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FindDoctorDTO {

    private DepartmentEnum department;

    private String town;

    private Long doctorId;

}
