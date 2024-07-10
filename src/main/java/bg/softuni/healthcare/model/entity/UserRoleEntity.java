package bg.softuni.healthcare.model.entity;

import bg.softuni.healthcare.model.entity.enums.UserRoleEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity {

    @NotNull
    @Column(name = "role", nullable = false, unique = true)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public UserRoleEntity() {
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public UserRoleEntity setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }
}
