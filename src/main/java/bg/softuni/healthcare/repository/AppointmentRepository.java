package bg.softuni.healthcare.repository;

import bg.softuni.healthcare.model.dto.AppointmentDTO;
import bg.softuni.healthcare.model.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {

    List<AppointmentEntity> findAllByPatientId(Long patient);

}
