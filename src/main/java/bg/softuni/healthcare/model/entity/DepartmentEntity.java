package bg.softuni.healthcare.model.entity;


import bg.softuni.healthcare.model.entity.enums.DepartmentEnum;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
public class DepartmentEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    @Enumerated(value = EnumType.STRING)
    private DepartmentEnum name;

    @OneToMany(mappedBy = "department")
    private List<DoctorEntity> doctors;

    public DepartmentEntity() {
        this.doctors = new ArrayList<>();
    }

    public DepartmentEnum getName() {
        return name;
    }

    public void setName(DepartmentEnum name) {
        this.name = name;
    }

    public List<DoctorEntity> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<DoctorEntity> doctors) {
        this.doctors = doctors;
    }
}
