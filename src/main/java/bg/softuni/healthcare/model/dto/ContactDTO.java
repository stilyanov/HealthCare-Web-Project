package bg.softuni.healthcare.model.dto;

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
public class ContactDTO {

    private Long id;

    @NotNull(message = "First name is required")
    @Size(min = 3, max = 20, message = "First Name length must be between 3 and 20 characters!")
    private String firstName;

    @NotNull(message = "Last name is required")
    @Size(min = 3, max = 20, message = "Last Name length must be between 3 and 20 characters!")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email
    private String email;

    @NotNull(message = "Subject is required")
    @Size(min = 5, max = 20, message = "Subject length must be between 3 and 50 characters!")
    private String subject;

    @NotNull(message = "Message is required")
    @Size(min = 20, max = 1000, message = "Message length must be between 20 and 1000 characters!")
    private String message;

}
