package bg.softuni.healthcare.service.impl;

import bg.softuni.healthcare.model.dto.AppointmentDTO;
import bg.softuni.healthcare.model.dto.FullAppointmentsInfoDTO;
import bg.softuni.healthcare.model.dto.UserAppointmentDTO;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

        if (isAppointmentTimeTaken(appointmentDTO.getDoctorId(), appointmentDateTime)) {
            throw new IllegalArgumentException("This appointment time is already taken.");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserEntity patient = userRepository.findByEmail(currentPrincipalName)
                .orElseThrow(() -> new IllegalArgumentException("User with email " + currentPrincipalName + " not found!"));

        AppointmentEntity appointment = modelMapper.map(appointmentDTO, AppointmentEntity.class);
        DoctorEntity doctor = doctorRepository.findById(appointmentDTO.getDoctorId())
                .orElseThrow(() -> new IllegalArgumentException("Doctor with id " + appointmentDTO.getDoctorId() + " not found!"));

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointmentRepository.save(appointment);
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
                    fullAppointmentsInfoDTO.setTime(appointment.getDateTime().format(dateTimeFormatter));
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

    @Override
    public List<UserAppointmentDTO> getUsersAppointments() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserEntity patient = this.userRepository.findByEmail(currentPrincipalName)
                .orElseThrow(() -> new IllegalArgumentException("User with email " + currentPrincipalName + " not found!"));

        return  appointmentRepository.findAllByPatientId(patient.getId())
                .stream()
                .map(appointment -> {
                    UserAppointmentDTO userAppointmentDTO = modelMapper.map(appointment, UserAppointmentDTO.class);
                    userAppointmentDTO.setDoctorFullName(appointment.getDoctor().getFullName());
                    userAppointmentDTO.setDepartment(appointment.getDoctor().getDepartment().getName());
                    userAppointmentDTO.setTime(appointment.getDateTime().format(dateTimeFormatter));
                    return userAppointmentDTO;
                })
                .toList();

    }

    @Override
    public List<LocalDateTime> getAvailableAppointmentTimes(Long doctorId, LocalDate date) {
        List<LocalDateTime> availableSlots = new ArrayList<>();
        LocalTime startTime = LocalTime.of(8, 0);
        LocalTime endTime = LocalTime.of(17, 0);
        LocalDateTime now = LocalDateTime.now();

        while (startTime.isBefore(endTime)) {
            LocalDateTime slot = LocalDateTime.of(date, startTime);
            if (!isAppointmentTimeTaken(doctorId, slot) && slot.isAfter(now)) {
                availableSlots.add(slot);
            }
            startTime = startTime.plusMinutes(30);
        }
        return availableSlots;
    }

    private boolean isAppointmentTimeTaken(Long doctorId, LocalDateTime appointmentDateTime) {
        return appointmentRepository.findByDoctorIdAndDateTime(doctorId, appointmentDateTime).isPresent();
    }

    private boolean isValidAppointmentTime(LocalDateTime appointmentDateTime) {
        DayOfWeek day = appointmentDateTime.getDayOfWeek();
        LocalTime time = appointmentDateTime.toLocalTime();
        return isWeekday(day) && isWithinWorkingHours(time);
    }

    private boolean isWeekday(DayOfWeek day) {
        return day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY;
    }

    private boolean isWithinWorkingHours(LocalTime time) {
        LocalTime startTime = LocalTime.of(8, 0); // 08:00
        LocalTime endTime = LocalTime.of(17, 0); // 17:00
        return !time.isBefore(startTime) && time.isBefore(endTime);
    }
}
