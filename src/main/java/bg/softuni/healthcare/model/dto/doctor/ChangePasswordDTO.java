package bg.softuni.healthcare.model.dto.doctor;

import bg.softuni.healthcare.validation.annotation.ValidatePasswords;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@ValidatePasswords
@NoArgsConstructor
public class ChangePasswordDTO {

    @NotNull(message = "Password cannot be empty!")
    @Size(min = 8, max = 20, message = "Password length must be between 8 and 20 characters")
    private String password;

    @NotNull(message = "Confirm password cannot be empty!")
    @Size(min = 8, max = 20, message = "Confirm password length must be between 8 and 20 characters")
    private String confirmPassword;
}
