package bg.softuni.healthcare.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class AppointmentEntity extends BaseEntity {

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    @ManyToOne(optional = false)
    @JoinColumn(name = "doctor_id")
    private DoctorEntity doctor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;

    private String reason;

    public AppointmentEntity() {
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime dateTime) {
        this.time = dateTime;
    }

    public DoctorEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorEntity doctor) {
        this.doctor = doctor;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
