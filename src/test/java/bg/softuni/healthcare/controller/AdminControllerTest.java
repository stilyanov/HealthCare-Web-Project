package bg.softuni.healthcare.controller;

import bg.softuni.healthcare.model.dto.ContactDTO;
import bg.softuni.healthcare.model.dto.appointment.FullAppointmentsInfoDTO;
import bg.softuni.healthcare.model.dto.user.UserProfileDTO;
import bg.softuni.healthcare.model.entity.UserEntity;
import bg.softuni.healthcare.service.AppointmentApiService;
import bg.softuni.healthcare.service.ContactService;
import bg.softuni.healthcare.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {

    @Mock
    private UserService mockUserService;

    @Mock
    private AppointmentApiService mockAppointmentService;

    @Mock
    private ContactService mockContactService;

    @InjectMocks
    private AdminController adminController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }

    @Test
    void testGetAdminPanel() throws Exception {
        UserEntity user = new UserEntity();
        List<UserProfileDTO> allUsers = List.of(new UserProfileDTO(), new UserProfileDTO());
        List<FullAppointmentsInfoDTO> allAppointments = List.of(new FullAppointmentsInfoDTO());
        List<ContactDTO> allContacts = List.of(new ContactDTO());

        given(mockUserService.getAllUsers(user)).willReturn(allUsers);
        given(mockAppointmentService.getAllFullAppointmentsInfo()).willReturn(allAppointments);
        given(mockContactService.getAllContacts()).willReturn(allContacts);

        mockMvc.perform(get("/admin/panel").flashAttr("user", user))
                .andExpect(status().isOk())
                .andExpect(view().name("admin-panel"))
                .andExpect(model().attributeExists("patientUsers", "doctorUsers", "allAppointments", "allContacts"));

        verify(mockUserService).getAllUsers(user);
        verify(mockAppointmentService).getAllFullAppointmentsInfo();
        verify(mockContactService).getAllContacts();
    }

    @Test
    void testDeleteUser() throws Exception {
        Long userId = 1L;

        mockMvc.perform(delete("/admin/delete/user/{id}", userId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/panel"))
                .andExpect(flash().attributeExists("success"));

        verify(mockUserService).deleteUser(userId);
    }

    @Test
    void testDeleteDoctor() throws Exception {
        Long doctorId = 1L;

        mockMvc.perform(delete("/admin/delete/doctor/{id}", doctorId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/panel"))
                .andExpect(flash().attributeExists("success"));

        verify(mockUserService).deleteUser(doctorId);
    }

    @Test
    void testDeleteAppointment() throws Exception {
        Long appointmentId = 1L;

        mockMvc.perform(delete("/admin/delete/appointment/{id}", appointmentId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/panel"))
                .andExpect(flash().attributeExists("success"));

        verify(mockAppointmentService).deleteAppointment(appointmentId);
    }

    @Test
    void testDeleteContact() throws Exception {
        Long contactId = 1L;

        mockMvc.perform(delete("/admin/delete/contact/{id}", contactId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/panel"))
                .andExpect(flash().attributeExists("success"));

        verify(mockContactService).deleteContact(contactId);
    }
}