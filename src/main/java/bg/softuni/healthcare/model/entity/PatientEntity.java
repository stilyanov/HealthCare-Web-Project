package bg.softuni.healthcare.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "patients")
@Getter
@Setter
public class PatientEntity extends BaseEntity {

    @Column(name = "first_name", nullable = false,unique = true)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    private Date dateOfBirth;

    @OneToMany(mappedBy = "patient")
    private List<AppointmentEntity> appointments;

    @OneToMany(mappedBy = "patient")
    private List<MedicalRecordEntity> medicalRecords;

    public PatientEntity() {
        this.appointments = new ArrayList<>();
        this.medicalRecords = new ArrayList<>();
    }
}
