package bg.softuni.healthcare.service.impl;

import bg.softuni.healthcare.model.dto.AddAppointmentDTO;
import bg.softuni.healthcare.model.dto.FullAppointmentsInfoDTO;
import bg.softuni.healthcare.model.dto.UserAppointmentDTO;
import bg.softuni.healthcare.service.AppointmentApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentApiServiceImpl implements AppointmentApiService {

    private final RestClient appointmentsRestClient;

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
        String url = "/appointments/all";
        ResponseEntity<List<UserAppointmentDTO>> response = appointmentsRestClient.get().uri(url)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<>() {});
        return response.getBody();
    }

    @Override
    public List<FullAppointmentsInfoDTO> getAllFullAppointmentsInfo() {
        String url = "/appointments/full-info?";
        ResponseEntity<List<FullAppointmentsInfoDTO>> response = appointmentsRestClient.get().uri(url)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<FullAppointmentsInfoDTO>>() {
                });
        return response.getBody();
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentsRestClient.delete().uri("/appointments/delete/" + id)
                .retrieve()
                .toBodilessEntity();

    }

}
