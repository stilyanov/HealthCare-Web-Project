package bg.softuni.healthcare.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
public class DepartmentEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "department")
    private List<DoctorEntity> doctors;

    public DepartmentEntity() {
        this.doctors = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DoctorEntity> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<DoctorEntity> doctors) {
        this.doctors = doctors;
    }
}
