package bg.softuni.healthcare.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "patients")
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<AppointmentEntity> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentEntity> appointments) {
        this.appointments = appointments;
    }

    public List<MedicalRecordEntity> getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(List<MedicalRecordEntity> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }
}
