package bg.softuni.healthcare.service.impl;

import bg.softuni.healthcare.model.dto.AppointmentDTO;
import bg.softuni.healthcare.model.dto.FullAppointmentsInfoDTO;
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
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;
    private final DateTimeFormatter dateTimeFormatter;

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

    @Override
    public List<AppointmentDTO> getAllAppointments() {
        return appointmentRepository.findAll()
                .stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentDTO.class))
                .toList();
    }

    @Override
    public List<FullAppointmentsInfoDTO> getAllFullAppointmentsInfo() {
        return appointmentRepository.findAll()
                .stream()
                .map(appointment -> {
                    FullAppointmentsInfoDTO fullAppointmentsInfoDTO = modelMapper.map(appointment, FullAppointmentsInfoDTO.class);
                    fullAppointmentsInfoDTO.setDoctorFullName(appointment.getDoctor().getFullName());
                    fullAppointmentsInfoDTO.setPatientFullName(appointment.getPatient().getFullName());
                    fullAppointmentsInfoDTO.setTime(appointment.getTime().format(dateTimeFormatter));
                    return fullAppointmentsInfoDTO;
                })
                .toList();
    }

    @Override
    public void deleteAppointment(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities()
                .stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
        if (!isAdmin) {
            throw new IllegalArgumentException("You do not have permission to delete appointments");
        }
        this.appointmentRepository.deleteById(id);
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
