package bg.softuni.healthcare.service.impl;

import bg.softuni.healthcare.model.dto.AppointmentDTO;
import bg.softuni.healthcare.model.entity.AppointmentEntity;
import bg.softuni.healthcare.model.entity.DoctorEntity;
import bg.softuni.healthcare.model.entity.UserEntity;
import bg.softuni.healthcare.repository.AppointmentRepository;
import bg.softuni.healthcare.repository.DoctorRepository;
import bg.softuni.healthcare.repository.UserRepository;
import bg.softuni.healthcare.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    @Override
    public void bookAppointment(AppointmentDTO appointmentDTO) {
        LocalDateTime appointmentDateTime = appointmentDTO.getTime();
        if (!isValidAppointmentTime(appointmentDateTime)) {
            throw new IllegalArgumentException("Appointment must be on a weekday between 08:00 and 17:00.");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserEntity patient = this.userRepository.findByEmail(currentPrincipalName)
                .orElseThrow(() -> new IllegalArgumentException("User with email " + currentPrincipalName + " not found!"));

        AppointmentEntity appointment = this.modelMapper.map(appointmentDTO, AppointmentEntity.class);

        DoctorEntity doctor = this.doctorRepository.findById(appointmentDTO.getDoctorId())
                .orElseThrow(() -> new IllegalArgumentException("Doctor with id " + appointmentDTO.getDoctorId() + " not found!"));

//        UserEntity patient = this.userRepository.findById(appointmentDTO.getPatientId())
//                .orElseThrow(() -> new IllegalArgumentException("User with id " + appointmentDTO.getPatientId() + " not found!"));

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        this.appointmentRepository.save(appointment);
    }

    private boolean isValidAppointmentTime(LocalDateTime appointmentDateTime) {
        DayOfWeek day = appointmentDateTime.getDayOfWeek();
        LocalTime time = appointmentDateTime.toLocalTime();
        LocalTime startTime = LocalTime.of(8, 0); // 08:00
        LocalTime endTime = LocalTime.of(17, 0); // 17:00

        boolean isWeekday = day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY;
        boolean isWithinWorkingHours = !time.isBefore(startTime) && time.isBefore(endTime);

        return isWeekday && isWithinWorkingHours;
    }
}
