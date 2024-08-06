package bg.softuni.healthcare.repository;

import bg.softuni.healthcare.model.entity.PatientResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PatientResultRepository extends JpaRepository<PatientResultEntity, Long> {

    List<PatientResultEntity> findAllByPatientId(Long patientId);

    void deleteByPatientId(Long id);
}
