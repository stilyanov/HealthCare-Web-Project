package bg.softuni.healthcare.model.dto.doctor;

import bg.softuni.healthcare.model.entity.enums.DepartmentEnum;
import jakarta.validation.constraints.*;

public class AddDoctorDTO {

    @NotNull(message = "First Name cannot be empty!")
    @Size(min = 3, max = 20, message = "First Name length must be between 3 and 20 characters!")
    private String firstName;

    @NotNull(message = "Last Name cannot be empty!")
    @Size(min = 3, max = 20, message = "Last Name length must be between 3 and 20 characters!")
    private String lastName;

    @NotNull(message = "Bio cannot be empty!")
    @Size(min = 10, max = 200, message = "Bio length must be between 10 and 200 characters!")
    private String bio;

    @NotNull(message = "Experience cannot be empty!")
    @Positive(message = "Experience cannot be negative!")
    @Min(value = 1, message = "Experience must be at least 1 year!")
    @Max(value = 50, message = "Experience cannot be more than 50 years!")
    private Integer experience;

    @NotNull(message = "Department cannot be empty!")
    private DepartmentEnum department;

    @NotBlank(message = "Please enter valid image URL!")
    @Size(max = 150)
    @Pattern(regexp = "https://.*", message = "Please enter valid image URL!")
    private String imageUrl;

    private Long addedBy;

    public AddDoctorDTO() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public DepartmentEnum getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEnum department) {
        this.department = department;
    }

    public Long getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(Long addedBy) {
        this.addedBy = addedBy;
    }
}
