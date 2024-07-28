package bg.softuni.healthcare.service;

import bg.softuni.healthcare.model.dto.AddAppointmentDTO;
import bg.softuni.healthcare.model.dto.FullAppointmentsInfoDTO;
import bg.softuni.healthcare.model.dto.UserAppointmentDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentApiService {

    List<LocalDateTime> getAvailableAppointmentTimes(Long doctorId, LocalDate date);

    void bookAppointment(AddAppointmentDTO appointmentDTO);

    List<UserAppointmentDTO> getUserAppointments(Long userId);

    List<FullAppointmentsInfoDTO> getAllFullAppointmentsInfo();

    void deleteAppointment(Long id);
}
