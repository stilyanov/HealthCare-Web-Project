package bg.softuni.healthcare.service;


import bg.softuni.healthcare.model.dto.appointment.DoctorAppointmentDTO;
import bg.softuni.healthcare.model.dto.patientResult.AddPatientResultDTO;
import bg.softuni.healthcare.model.dto.patientResult.PatientResultDTO;

import java.util.List;

public interface PatientResultService {

    void addPatientResult(AddPatientResultDTO patientResultDTO);

    List<PatientResultDTO> getPatientResultsByPatientId(Long patientId);

    void deletePatientResultById(Long id);

}
