package bg.softuni.healthcare.model.dto;

import bg.softuni.healthcare.model.entity.enums.DepartmentEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AllDoctorsDTO {

    private Long id;

    private String imageUrl;

    private String firstName;

    private String lastName;

    private DepartmentEnum department;

    private Integer experience;

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
