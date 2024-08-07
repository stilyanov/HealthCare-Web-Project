package bg.softuni.healthcare.controller;

import bg.softuni.healthcare.model.dto.doctor.AddDoctorDTO;
import bg.softuni.healthcare.model.dto.doctor.DoctorDTO;
import bg.softuni.healthcare.model.dto.doctor.InfoDoctorDTO;
import bg.softuni.healthcare.model.entity.enums.DepartmentEnum;
import bg.softuni.healthcare.service.DepartmentService;
import bg.softuni.healthcare.service.DoctorService;
import bg.softuni.healthcare.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DoctorController.class)
public class DoctorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoctorService doctorService;

    @MockBean
    private DepartmentService departmentService;

    @MockBean
    private UserService userService;

    private List<DoctorDTO> allDoctors;
    private InfoDoctorDTO doctorInfo;

    @BeforeEach
    public void setUp() {
        allDoctors = new ArrayList<>();
        doctorInfo = new InfoDoctorDTO();

        when(doctorService.getAllDoctors()).thenReturn(allDoctors);
        when(doctorService.getDoctorInfo(any(Long.class))).thenReturn(doctorInfo);
        when(doctorService.getAllTowns()).thenReturn(List.of("Town1", "Town2"));
        when(departmentService.findByDepartment(any(DepartmentEnum.class))).thenReturn(allDoctors);
        when(departmentService.findByTown(any(String.class))).thenReturn(allDoctors);
        when(doctorService.findByName(any(String.class))).thenReturn(allDoctors);
    }

    @Test
    @WithMockUser
    public void testGetDoctors() throws Exception {
        mockMvc.perform(get("/doctors/all"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("allDoctors", allDoctors))
                .andExpect(view().name("all-doctors"));
    }


    @Test
    @WithMockUser
    public void testAddDoctor() throws Exception {
        mockMvc.perform(get("/doctors/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-doctor"));
    }


    @Test
    @WithMockUser
    public void testFindDoctorByDepartment() throws Exception {
        mockMvc.perform(get("/doctors/find").param("department", "CARDIOLOGY"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("doctors", allDoctors))
                .andExpect(view().name("department-search"));
    }

    @Test
    @WithMockUser
    public void testFindDoctorByTown() throws Exception {
        mockMvc.perform(get("/doctors/find").param("town", "Town1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("doctors", allDoctors))
                .andExpect(view().name("town-search"));
    }

    @Test
    @WithMockUser
    public void testFindDoctorByName() throws Exception {
        mockMvc.perform(get("/doctors/find").param("name", "Dr. Smith"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("doctors", allDoctors))
                .andExpect(view().name("name-search"));
    }

    @Test
    @WithMockUser
    public void testFindDoctorNoParams() throws Exception {
        mockMvc.perform(get("/doctors/find"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("doctors", List.of()))
                .andExpect(view().name("find-doctor"));
    }
}