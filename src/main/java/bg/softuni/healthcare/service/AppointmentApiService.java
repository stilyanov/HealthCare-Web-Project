package bg.softuni.healthcare.service;

import bg.softuni.healthcare.model.dto.appointment.AddAppointmentDTO;
import bg.softuni.healthcare.model.dto.appointment.DoctorAppointmentDTO;
import bg.softuni.healthcare.model.dto.appointment.FullAppointmentsInfoDTO;
import bg.softuni.healthcare.model.dto.user.UserAppointmentDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentApiService {

    List<LocalDateTime> getAvailableAppointmentTimes(Long doctorId, LocalDate date);

    void bookAppointment(AddAppointmentDTO appointmentDTO);

    List<UserAppointmentDTO> getUserAppointments(Long userId);

    List<FullAppointmentsInfoDTO> getAllFullAppointmentsInfo();

    void deleteAppointment(Long id);

    List<UserAppointmentDTO> getAppointmentsByPatientId(Long userId);

    List<DoctorAppointmentDTO> getAppointmentsByDoctorId(Long userId);
}
