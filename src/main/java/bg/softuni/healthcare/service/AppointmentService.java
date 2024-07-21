package bg.softuni.healthcare.service;

import bg.softuni.healthcare.model.dto.AppointmentDTO;

public interface AppointmentService {
    void bookAppointment(AppointmentDTO appointmentDTO);
}
