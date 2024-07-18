package bg.softuni.healthcare.repository;

import bg.softuni.healthcare.model.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {
    List<DoctorEntity> findByTown(String town);

    @Query("SELECT DISTINCT d.town FROM DoctorEntity d")
    List<String> findAllTowns();
}
