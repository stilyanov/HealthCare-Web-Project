package bg.softuni.healthcare.service;

import bg.softuni.healthcare.model.dto.AppointmentDTO;
import bg.softuni.healthcare.model.dto.FullAppointmentsInfoDTO;
import bg.softuni.healthcare.model.dto.UserAppointmentDTO;

import java.util.List;

public interface AppointmentService {
    void bookAppointment(AppointmentDTO appointmentDTO);

    List<AppointmentDTO> getAllAppointments();

    List<FullAppointmentsInfoDTO> getAllFullAppointmentsInfo();

    void deleteAppointment(Long id);

    List<UserAppointmentDTO> getUsersAppointments();
}
