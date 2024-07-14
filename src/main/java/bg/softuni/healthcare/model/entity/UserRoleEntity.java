package bg.softuni.healthcare.model.entity;

import bg.softuni.healthcare.model.entity.enums.UserRoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity {

    @Column(name = "role", nullable = false, unique = true)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public UserRoleEntity setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }
}
