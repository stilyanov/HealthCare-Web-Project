package bg.softuni.healthcare.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "prescriptions")
public class PrescriptionEntity extends BaseEntity {

    @Column(nullable = false)
    private String medication;

    @Column(nullable = false)
    private String dosage;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;

    public PrescriptionEntity() {
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medicine) {
        this.medication = medicine;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }
}
