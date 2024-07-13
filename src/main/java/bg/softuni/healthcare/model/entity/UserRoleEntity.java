package bg.softuni.healthcare.model.entity;

import bg.softuni.healthcare.model.entity.enums.UserRoleEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class UserRoleEntity extends BaseEntity {

    @Column(name = "role", nullable = false, unique = true)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;
}
