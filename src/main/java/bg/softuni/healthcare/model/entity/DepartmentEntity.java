package bg.softuni.healthcare.model.entity;


import bg.softuni.healthcare.model.entity.enums.DepartmentEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
@Getter
@Setter
public class DepartmentEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    @Enumerated(value = EnumType.STRING)
    private DepartmentEnum name;

    @OneToMany(mappedBy = "department")
    private List<DoctorEntity> doctors;

    public DepartmentEntity() {
        this.doctors = new ArrayList<>();
    }
}
