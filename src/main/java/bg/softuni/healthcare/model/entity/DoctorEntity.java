package bg.softuni.healthcare.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctors")
@Getter
@Setter
public class DoctorEntity extends BaseEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String bio;

    @Column(nullable = false)
    private Integer experience;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String city;

    @OneToMany(mappedBy = "doctor")
    private List<AppointmentEntity> appointments;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private DepartmentEntity department;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    public DoctorEntity() {
        this.appointments = new ArrayList<>();
    }
}
