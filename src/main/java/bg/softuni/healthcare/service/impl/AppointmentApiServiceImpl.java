package bg.softuni.healthcare.service.impl;

import bg.softuni.healthcare.model.dto.AddAppointmentDTO;
import bg.softuni.healthcare.model.dto.FullAppointmentsInfoDTO;
import bg.softuni.healthcare.model.dto.UserAppointmentDTO;
import bg.softuni.healthcare.repository.UserRepository;
import bg.softuni.healthcare.service.AppointmentApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentApiServiceImpl implements AppointmentApiService {

    private final RestClient appointmentsRestClient;
    private final DateTimeFormatter dateTimeFormatter;
    private final UserRepository userRepository;

    @Override
    public List<LocalDateTime> getAvailableAppointmentTimes(Long doctorId, LocalDate date) {
        String url = String.format("/available?doctorId=%d&date=%s", doctorId, date);
        ResponseEntity<List<LocalDateTime>> response = appointmentsRestClient.get().uri(url)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<LocalDateTime>>() {
                });
        return response.getBody();
    }

    @Override
    public void bookAppointment(AddAppointmentDTO appointmentDTO) {
        appointmentsRestClient.post().uri("/book")
                .body(appointmentDTO)
                .retrieve()
                .toBodilessEntity();
    }

    @Override
    public List<UserAppointmentDTO> getUsersAppointments() {
        String url = "/appointments/";
        ResponseEntity<List<UserAppointmentDTO>> response = appointmentsRestClient.get().uri(url)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<>() {});
        List<UserAppointmentDTO> appointments = response.getBody();
        if (appointments != null) {
            appointments.forEach(appointment -> appointment.setFormattedDateTime(appointment.getDateTime().format(dateTimeFormatter)));
        }
        return appointments;
    }

    @Override
    public List<FullAppointmentsInfoDTO> getAllFullAppointmentsInfo() {
        String url = "/appointments/all";
        ResponseEntity<List<FullAppointmentsInfoDTO>> response = appointmentsRestClient.get().uri(url)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<>() {});

        List<FullAppointmentsInfoDTO> appointments = response.getBody();
        if (appointments != null) {
            appointments.forEach(appointment -> {
                String patientFullName = userRepository.findById(appointment.getPatientId())
                        .map(user -> user.getFirstName() + " " + user.getLastName())
                        .orElse(null);
                String doctorFullName = userRepository.findById(appointment.getDoctorId())
                        .map(user -> user.getFirstName() + " " + user.getLastName())
                        .orElse(null);
                appointment.setFormattedDateTime(appointment.getDateTime().format(dateTimeFormatter));
                appointment.setPatientFullName(patientFullName);
                appointment.setDoctorFullName(doctorFullName);
            });
        }
        return appointments;
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentsRestClient.delete().uri("/appointments/delete/" + id)
                .retrieve()
                .toBodilessEntity();

    }

}
