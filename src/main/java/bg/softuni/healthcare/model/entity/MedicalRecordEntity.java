package bg.softuni.healthcare.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "medical_records")
public class MedicalRecordEntity extends BaseEntity {

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String diagnosis;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;

    public MedicalRecordEntity() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }
}
