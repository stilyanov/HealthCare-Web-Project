package bg.softuni.healthcare.model.dto.user;

import bg.softuni.healthcare.validation.annotation.UniqueEmail;
import bg.softuni.healthcare.validation.annotation.ValidatePasswords;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@ValidatePasswords
public class UserRegisterDTO {

    @NotNull(message = "First Name cannot be empty!")
    @Size(min = 3, max = 20, message = "First Name length must be between 3 and 20 characters!")
    private String firstName;

    @NotNull(message = "Last Name cannot be empty!")
    @Size(min = 3, max = 20, message = "Last Name length must be between 3 and 20 characters!")
    private String lastName;

    @Email
    @NotBlank(message = "Email cannot be empty!")
    @UniqueEmail
    private String email;

    @NotNull(message = "Password cannot be empty!")
    @Size(min = 8, max = 20, message = "Password length must be between 8 and 20 characters")
    private String password;

    @NotNull(message = "Confirm password cannot be empty!")
    @Size(min = 8, max = 20, message = "Confirm password length must be between 8 and 20 characters")
    private String confirmPassword;
}
