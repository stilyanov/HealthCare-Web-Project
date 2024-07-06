package bg.softuni.healthcare.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "prescriptions")
public class PrescriptionEntity extends BaseEntity {

    private String medication;

    private String dosage;

    @ManyToOne
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
}
