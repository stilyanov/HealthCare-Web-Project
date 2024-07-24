package bg.softuni.healthcare.repository;

import bg.softuni.healthcare.model.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {

    List<AppointmentEntity> findAllByPatientId(Long patient);

    Optional<AppointmentEntity> findByDoctorIdAndDateTime(Long doctorId, LocalDateTime dateTime);

}
