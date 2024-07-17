package bg.softuni.healthcare.model.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginDTO {

    @Email
    @NotNull(message = "Email cannot be empty!")
    private String email;

    @NotNull(message = "Password cannot be empty!")
    @Size(min = 8, max = 20, message = "Password length must be between 8 and 20 characters")
    private String password;
}
