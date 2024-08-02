package bg.softuni.healthcare.service.impl;

import bg.softuni.healthcare.model.dto.appointment.DoctorAppointmentDTO;
import bg.softuni.healthcare.model.dto.patientResult.AddPatientResultDTO;
import bg.softuni.healthcare.model.entity.PatientResultEntity;
import bg.softuni.healthcare.repository.PatientResultRepository;
import bg.softuni.healthcare.service.AppointmentApiService;
import bg.softuni.healthcare.service.DoctorService;
import bg.softuni.healthcare.service.PatientResultService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientResultServiceImpl implements PatientResultService {

    private final PatientResultRepository patientResultRepository;
    private final DoctorService doctorService;
    private final AppointmentApiService appointmentService;
    private final ModelMapper modelMapper;


    @Override
    public void addPatientResult(AddPatientResultDTO patientResultDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        Long doctorId = doctorService.getDoctorIdByEmail(currentUser);

        List<DoctorAppointmentDTO> appointments = appointmentService.getAppointmentsByDoctorId(doctorId);

        if (appointments == null || appointments.isEmpty()) {
            throw new IllegalArgumentException("No appointments found for the doctor!");
        }

        DoctorAppointmentDTO selectedAppointments = appointments
                .stream()
                .filter(appointment -> appointment.getId().equals(patientResultDTO.getAppointmentId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No appointment found with the given id!"));

        PatientResultEntity patientResult = modelMapper.map(patientResultDTO, PatientResultEntity.class);
        patientResult.setAppointmentId(selectedAppointments.getId());

        patientResultRepository.save(patientResult);
    }

    @Override
    public List<DoctorAppointmentDTO> getAppointmentsForCurrentDoctor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        Long doctorId = doctorService.getDoctorIdByEmail(currentUser);

        return appointmentService.getAppointmentsByDoctorId(doctorId);
    }
}
