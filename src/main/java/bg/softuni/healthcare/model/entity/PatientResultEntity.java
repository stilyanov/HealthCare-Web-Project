package bg.softuni.healthcare.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "patient_results")
public class PatientResultEntity extends BaseEntity {

    @Column(nullable = false)
    private String prescription;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String result;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private Long appointmentId;

}
