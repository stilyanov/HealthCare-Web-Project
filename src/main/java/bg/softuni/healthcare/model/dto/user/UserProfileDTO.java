package bg.softuni.healthcare.model.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserProfileDTO {

    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private List<String> roles;

    public UserProfileDTO() {
        this.roles = new ArrayList<>();
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
}
