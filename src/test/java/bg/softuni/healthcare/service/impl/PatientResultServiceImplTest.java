
package bg.softuni.healthcare.service.impl;

import bg.softuni.healthcare.model.dto.appointment.DoctorAppointmentDTO;
import bg.softuni.healthcare.model.dto.patientResult.AddPatientResultDTO;
import bg.softuni.healthcare.model.dto.patientResult.PatientResultDTO;
import bg.softuni.healthcare.model.entity.PatientResultEntity;
import bg.softuni.healthcare.model.entity.UserEntity;
import bg.softuni.healthcare.repository.PatientResultRepository;
import bg.softuni.healthcare.service.AppointmentApiService;
import bg.softuni.healthcare.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PatientResultServiceImplTest {

    @Mock
    private PatientResultRepository patientResultRepository;

    @Mock
    private AppointmentApiService appointmentService;

    @Mock
    private UserService userService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PatientResultServiceImpl patientResultService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddPatientResult() {
        AddPatientResultDTO patientResultDTO = new AddPatientResultDTO();
        patientResultDTO.setPatientId(1L);
        patientResultDTO.setAppointmentId(1L);
        patientResultDTO.setPrescription("Prescription");
        patientResultDTO.setResult("Result");
        patientResultDTO.setDate(LocalDate.parse("2024-08-07"));

        UserEntity userEntity = new UserEntity();
        DoctorAppointmentDTO appointmentDTO = new DoctorAppointmentDTO();
        appointmentDTO.setId(1L);

        when(userService.getUserById(1L)).thenReturn(userEntity);
        when(appointmentService.getAppointmentById(1L)).thenReturn(appointmentDTO);

        PatientResultEntity patientResultEntity = new PatientResultEntity();
        patientResultEntity.setPrescription(patientResultDTO.getPrescription());
        patientResultEntity.setResult(patientResultDTO.getResult());
        patientResultEntity.setAppointmentId(appointmentDTO.getId());
        patientResultEntity.setPatient(userEntity);
        patientResultEntity.setDate(patientResultDTO.getDate());

        patientResultService.addPatientResult(patientResultDTO);

        verify(patientResultRepository, times(1)).save(any(PatientResultEntity.class));
    }

    @Test
    public void testAddPatientResult_UserNotFound() {
        AddPatientResultDTO patientResultDTO = new AddPatientResultDTO();
        patientResultDTO.setPatientId(1L);
        patientResultDTO.setAppointmentId(1L);

        when(userService.getUserById(1L)).thenThrow(new IllegalArgumentException("User not found"));

        assertThrows(IllegalArgumentException.class, () -> patientResultService.addPatientResult(patientResultDTO));
    }

    @Test
    public void testAddPatientResult_AppointmentNotFound() {
        AddPatientResultDTO patientResultDTO = new AddPatientResultDTO();
        patientResultDTO.setPatientId(1L);
        patientResultDTO.setAppointmentId(1L);

        UserEntity userEntity = new UserEntity();
        when(userService.getUserById(1L)).thenReturn(userEntity);
        when(appointmentService.getAppointmentById(1L)).thenThrow(new IllegalArgumentException("Appointment not found"));

        assertThrows(IllegalArgumentException.class, () -> patientResultService.addPatientResult(patientResultDTO));
    }

    @Test
    public void testGetPatientResultsByPatientId() {
        PatientResultEntity patientResultEntity = new PatientResultEntity();
        patientResultEntity.setId(1L);
        List<PatientResultEntity> patientResults = List.of(patientResultEntity);

        when(patientResultRepository.findAllByPatientId(1L)).thenReturn(patientResults);
        when(modelMapper.map(patientResultEntity, PatientResultDTO.class)).thenReturn(new PatientResultDTO());

        List<PatientResultDTO> result = patientResultService.getPatientResultsByPatientId(1L);
        assertEquals(1, result.size());
    }

    @Test
    public void testGetPatientResultsByPatientId_NoResults() {
        when(patientResultRepository.findAllByPatientId(1L)).thenReturn(List.of());

        List<PatientResultDTO> result = patientResultService.getPatientResultsByPatientId(1L);
        assertEquals(0, result.size());
    }

    @Test
    public void testDeletePatientResultById() {
        Long id = 1L;
        doNothing().when(patientResultRepository).deleteById(id);

        patientResultService.deletePatientResultById(id);
        verify(patientResultRepository, times(1)).deleteById(id);
    }
}