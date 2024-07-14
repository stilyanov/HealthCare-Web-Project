package bg.softuni.healthcare.model.dto.doctor;

import bg.softuni.healthcare.model.entity.enums.DepartmentEnum;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddDoctorDTO {

    @NotNull(message = "First Name cannot be empty!")
    @Size(min = 3, max = 20, message = "First Name length must be between 3 and 20 characters!")
    private String firstName;

    @NotNull(message = "Last Name cannot be empty!")
    @Size(min = 3, max = 20, message = "Last Name length must be between 3 and 20 characters!")
    private String lastName;

    @NotNull(message = "City cannot be empty!")
    @Size(min = 3, max = 20, message = "City length must be between 3 and 20 characters!")
    private String city;

    @NotNull(message = "Bio cannot be empty!")
    @Size(min = 50, max = 1000, message = "Bio length must be between 50 and 400 characters!")
    private String bio;

    @NotNull(message = "Experience cannot be empty!")
    @Positive(message = "Experience cannot be negative!")
    @Min(value = 1, message = "Experience must be at least 1 year!")
    @Max(value = 50, message = "Experience cannot be more than 50 years!")
    private Integer experience;

    @NotNull(message = "Department cannot be empty!")
    private DepartmentEnum department;

    @NotBlank(message = "Please enter valid image URL!")
    @Size(max = 200, message = "Image URL length must be less than 200 characters!")
    @Pattern(regexp = "https://.*", message = "Please enter valid image URL!")
    private String imageUrl;

    private Long addedBy;
}