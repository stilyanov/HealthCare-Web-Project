package bg.softuni.healthcare.model.dto.doctor;

import bg.softuni.healthcare.model.entity.UserEntity;
import bg.softuni.healthcare.model.entity.enums.DepartmentEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class AllDoctorsDTO {

    private String imageUrl;

    private String firstName;

    private String lastName;

    private String bio;

    private DepartmentEnum department;

    private Integer experience;

    private String addedBy;

    private LocalDate createdOn;

    public String getFullName() {
        StringBuilder sb = new StringBuilder();
        if (firstName != null && !firstName.isEmpty()) {
            sb.append(firstName);
        }

        if (lastName != null && !lastName.isEmpty()) {
            sb.append(" ").append(lastName);
        }

        return sb.toString();
    }
}
