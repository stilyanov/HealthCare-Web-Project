package bg.softuni.healthcare.service.impl;

import bg.softuni.healthcare.model.entity.DoctorEntity;
import bg.softuni.healthcare.repository.DoctorRepository;
import bg.softuni.healthcare.repository.PrescriptionRepository;
import bg.softuni.healthcare.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorCleanupService {

    private final DoctorRepository doctorRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final UserRepository userRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorCleanupService.class);

    @Scheduled(cron = "0 0 0 * * ?") // Runs daily at midnight
    @Transactional
    public void cleanUpDoctors() {
        LOGGER.info("Starting doctor cleanup task...");

        LocalDate thirtyDaysAgo = LocalDate.from(LocalDateTime.now().minusDays(30));

        List<DoctorEntity> doctorsToDelete = doctorRepository.findAllByAddedTimeBeforeAndPasswordChangedIsFalse(thirtyDaysAgo);

        LOGGER.info("Found {} doctors to delete.", doctorsToDelete.size());

        for (DoctorEntity doctor : doctorsToDelete) {
            LOGGER.info("Processing doctor with ID: {}", doctor.getId());

            if (doctor.getUser() != null) {
                LOGGER.info("Deleting prescriptions for user with ID: {}", doctor.getId());
                prescriptionRepository.deleteByPatientId(doctor.getUser().getId());

                Long userId = doctor.getUser().getId();

                LOGGER.info("Deleting user with ID: {}", doctor.getUser().getId());

                doctorRepository.deleteById(doctor.getId());
                userRepository.deleteById(userId);
            } else {
                LOGGER.info("Deleting doctor with ID: {}", doctor.getId());

                doctorRepository.deleteById(doctor.getId());
            }
        }
        LOGGER.info("Completed doctor cleanup task.");
    }

}
