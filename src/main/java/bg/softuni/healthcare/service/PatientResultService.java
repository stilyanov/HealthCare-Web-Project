package bg.softuni.healthcare.service;


import bg.softuni.healthcare.model.dto.appointment.DoctorAppointmentDTO;
import bg.softuni.healthcare.model.dto.patientResult.AddPatientResultDTO;

import java.util.List;

public interface PatientResultService {

    void addPatientResult(AddPatientResultDTO patientResultDTO);

    List<DoctorAppointmentDTO> getAppointmentsForCurrentDoctor();
}
