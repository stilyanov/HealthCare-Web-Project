package bg.softuni.healthcare.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "prescriptions")
@Getter
@Setter
@NoArgsConstructor
public class PrescriptionEntity extends BaseEntity {

    @Column(nullable = false)
    private String medication;

    @Column(nullable = false)
    private String dosage;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;
}
