package bg.softuni.healthcare.service.impl;

import bg.softuni.healthcare.model.dto.doctor.AddDoctorDTO;
import bg.softuni.healthcare.model.dto.doctor.DoctorDTO;
import bg.softuni.healthcare.model.dto.doctor.InfoDoctorDTO;
import bg.softuni.healthcare.model.entity.DepartmentEntity;
import bg.softuni.healthcare.model.entity.DoctorEntity;
import bg.softuni.healthcare.model.entity.UserEntity;
import bg.softuni.healthcare.model.entity.UserRoleEntity;
import bg.softuni.healthcare.model.entity.enums.DepartmentEnum;
import bg.softuni.healthcare.model.entity.enums.UserRoleEnum;
import bg.softuni.healthcare.repository.DepartmentRepository;
import bg.softuni.healthcare.repository.DoctorRepository;
import bg.softuni.healthcare.repository.UserRepository;
import bg.softuni.healthcare.repository.UserRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceImplTest {

    private DoctorServiceImpl toTest;

    @Mock
    private DoctorRepository mockDoctorRepository;

    @Mock
    private DepartmentRepository mockDepartmentRepository;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private UserRoleRepository mockUserRoleRepository;

    @Mock
    private ModelMapper mockModelMapper;

    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @Mock
    private Authentication mockAuthentication;

    @Mock
    private SecurityContext mockSecurityContext;

    @Captor
    private ArgumentCaptor<DoctorEntity> doctorEntityCaptor;

    @Captor
    private ArgumentCaptor<UserEntity> userEntityCaptor;

    @BeforeEach
    void setUp() {
        toTest = new DoctorServiceImpl(
                mockDoctorRepository,
                mockDepartmentRepository,
                mockUserRepository,
                mockUserRoleRepository,
                mockModelMapper,
                mockPasswordEncoder
        );

        SecurityContextHolder.setContext(mockSecurityContext);
    }

    @Test
    void testAddDoctor() {
        // Arrange
        AddDoctorDTO addDoctorDTO = new AddDoctorDTO();
        addDoctorDTO.setEmail("doctor@example.com");
        addDoctorDTO.setFirstName("First");
        addDoctorDTO.setLastName("Last");
        addDoctorDTO.setPassword("password");
        addDoctorDTO.setDepartment(DepartmentEnum.CARDIOLOGY);

        UserEntity currentUser = new UserEntity();
        currentUser.setEmail("admin@example.com");

        UserRoleEntity doctorRole = new UserRoleEntity();
        doctorRole.setRole(UserRoleEnum.DOCTOR);

        DepartmentEntity department = new DepartmentEntity();
        department.setName(DepartmentEnum.CARDIOLOGY);

        when(mockSecurityContext.getAuthentication()).thenReturn(mockAuthentication);
        when(mockAuthentication.getPrincipal()).thenReturn(currentUser.getEmail());
        when(mockUserRepository.findByEmail(currentUser.getEmail())).thenReturn(Optional.of(currentUser));
        when(mockUserRoleRepository.findByRole(UserRoleEnum.DOCTOR)).thenReturn(doctorRole);
        when(mockPasswordEncoder.encode(addDoctorDTO.getPassword())).thenReturn("encodedPassword");
        when(mockDepartmentRepository.findByName(DepartmentEnum.CARDIOLOGY)).thenReturn(Optional.of(department));
        when(mockModelMapper.map(addDoctorDTO, DoctorEntity.class)).thenReturn(new DoctorEntity());

        // Act
        toTest.addDoctor(addDoctorDTO);

        // Assert
        verify(mockUserRepository).save(userEntityCaptor.capture());
        UserEntity savedUser = userEntityCaptor.getValue();
        assertEquals("doctor@example.com", savedUser.getEmail());
        assertEquals("encodedPassword", savedUser.getPassword());
        assertTrue(savedUser.getRoles().contains(doctorRole));

        verify(mockDoctorRepository).save(doctorEntityCaptor.capture());
        DoctorEntity savedDoctor = doctorEntityCaptor.getValue();
        assertEquals("encodedPassword", savedDoctor.getPassword());
        assertEquals(department, savedDoctor.getDepartment());
        assertEquals(LocalDate.now(), savedDoctor.getAddedTime());
        assertEquals(savedUser, savedDoctor.getUser());
    }

    @Test
    void testGetAllDoctors() {
        // Arrange
        DoctorEntity doctor = new DoctorEntity();
        DepartmentEntity department = new DepartmentEntity();
        department.setName(DepartmentEnum.CARDIOLOGY);
        doctor.setDepartment(department);

        when(mockDoctorRepository.findAll()).thenReturn(List.of(doctor));
        when(mockModelMapper.map(doctor, DoctorDTO.class)).thenReturn(new DoctorDTO());

        // Act
        List<DoctorDTO> result = toTest.getAllDoctors();

        // Assert
        assertEquals(1, result.size());
    }

    @Test
    void testGetDoctorInfo() {
        // Arrange
        Long doctorId = 1L;
        DoctorEntity doctor = new DoctorEntity();
        DepartmentEntity department = new DepartmentEntity();
        department.setName(DepartmentEnum.CARDIOLOGY);
        doctor.setDepartment(department);

        when(mockDoctorRepository.findById(doctorId)).thenReturn(Optional.of(doctor));
        when(mockModelMapper.map(doctor, InfoDoctorDTO.class)).thenReturn(new InfoDoctorDTO());

        // Act
        InfoDoctorDTO result = toTest.getDoctorInfo(doctorId);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testGetAllTowns() {
        // Arrange
        when(mockDoctorRepository.findAllTowns()).thenReturn(List.of("Town1", "Town2"));

        // Act
        List<String> result = toTest.getAllTowns();

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains("Town1"));
        assertTrue(result.contains("Town2"));
    }


    @Test
    void testDeleteDoctor_notAdmin() {
        // Arrange
        Long doctorId = 1L;

        when(mockSecurityContext.getAuthentication()).thenReturn(mockAuthentication);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> toTest.deleteDoctor(doctorId));
    }

    @Test
    void testFindByName() {
        // Arrange
        String name = "John";
        DoctorEntity doctor = new DoctorEntity();
        DepartmentEntity department = new DepartmentEntity();
        department.setName(DepartmentEnum.CARDIOLOGY);
        doctor.setDepartment(department);

        when(mockDoctorRepository.findByNameContainingIgnoreCase(name)).thenReturn(List.of(doctor));
        when(mockModelMapper.map(doctor, DoctorDTO.class)).thenReturn(new DoctorDTO());

        // Act
        List<DoctorDTO> result = toTest.findByName(name);

        // Assert
        assertEquals(1, result.size());
    }

    @Test
    void testGetDoctorById() {
        // Arrange
        Long doctorId = 1L;
        DoctorEntity doctor = new DoctorEntity();
        DepartmentEntity department = new DepartmentEntity();
        department.setName(DepartmentEnum.CARDIOLOGY);
        doctor.setDepartment(department);

        when(mockDoctorRepository.findById(doctorId)).thenReturn(Optional.of(doctor));
        when(mockModelMapper.map(doctor, DoctorDTO.class)).thenReturn(new DoctorDTO());

        // Act
        DoctorDTO result = toTest.getDoctorById(doctorId);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testFindByEmail() {
        // Arrange
        String email = "doctor@example.com";
        DoctorEntity doctor = new DoctorEntity();

        when(mockDoctorRepository.findByEmail(email)).thenReturn(Optional.of(doctor));

        // Act
        DoctorEntity result = toTest.findByEmail(email);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testChangePassword() {
        // Arrange
        DoctorEntity doctor = new DoctorEntity();
        doctor.setEmail("doctor@example.com");
        String newPassword = "newPassword";

        when(mockPasswordEncoder.encode(newPassword)).thenReturn("encodedNewPassword");

        // Act
        toTest.changePassword(doctor, newPassword);

        // Assert
        assertEquals("encodedNewPassword", doctor.getPassword());
        assertTrue(doctor.isPasswordChanged());
        verify(mockDoctorRepository).save(doctor);
        verify(mockUserRepository).updatePasswordByEmail(doctor.getEmail(), "encodedNewPassword");
    }

    @Test
    void testGetDoctorIdByEmail() {
        // Arrange
        String email = "doctor@example.com";
        DoctorEntity doctor = new DoctorEntity();
        doctor.setId(1L);
    }
}

