package bg.softuni.healthcare.service.impl;

import bg.softuni.healthcare.model.dto.appointment.DoctorAppointmentDTO;
import bg.softuni.healthcare.model.dto.patientResult.AddPatientResultDTO;
import bg.softuni.healthcare.model.entity.PatientResultEntity;
import bg.softuni.healthcare.model.entity.UserEntity;
import bg.softuni.healthcare.repository.PatientResultRepository;
import bg.softuni.healthcare.service.AppointmentApiService;
import bg.softuni.healthcare.service.PatientResultService;
import bg.softuni.healthcare.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientResultServiceImpl implements PatientResultService {

    private final PatientResultRepository patientResultRepository;
    private final AppointmentApiService appointmentService;
    private final UserService userService;


    @Override
    public void addPatientResult(AddPatientResultDTO patientResultDTO) {
        UserEntity patient = userService.getUserById(patientResultDTO.getPatientId());
        DoctorAppointmentDTO appointment = appointmentService.getAppointmentById(patientResultDTO.getAppointmentId());

        PatientResultEntity patientResult = new PatientResultEntity();
        patientResult.setPrescription(patientResultDTO.getPrescription());
        patientResult.setResult(patientResultDTO.getResult());
        patientResult.setAppointmentId(appointment.getId());
        patientResult.setPatient(patient);
        patientResult.setDate(patientResultDTO.getDate());

        patientResultRepository.save(patientResult);
    }
}
