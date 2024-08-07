package bg.softuni.healthcare.service.impl;

import bg.softuni.healthcare.model.dto.user.UserProfileDTO;
import bg.softuni.healthcare.model.dto.user.UserRegisterDTO;
import bg.softuni.healthcare.model.entity.UserEntity;
import bg.softuni.healthcare.model.entity.UserRoleEntity;
import bg.softuni.healthcare.model.entity.enums.UserRoleEnum;
import bg.softuni.healthcare.repository.DoctorRepository;
import bg.softuni.healthcare.repository.PatientResultRepository;
import bg.softuni.healthcare.repository.UserRepository;
import bg.softuni.healthcare.repository.UserRoleRepository;
import bg.softuni.healthcare.service.AppointmentApiService;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    private UserServiceImpl toTest;

    @Captor
    private ArgumentCaptor<UserEntity> userEntityCaptor;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private DoctorRepository mockDoctorRepository;

    @Mock
    private UserRoleRepository mockUserRoleRepository;

    @Mock
    private PatientResultRepository mockPatientResultRepository;

    @Mock
    private AppointmentApiService mockAppointmentApiService;

    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @Mock
    private Authentication mockAuthentication;

    @Mock
    private SecurityContext mockSecurityContext;

    @BeforeEach
    void setUp() {
        toTest = new UserServiceImpl(
                mockUserRepository,
                mockDoctorRepository,
                new ModelMapper(),
                mockPasswordEncoder,
                mockUserRoleRepository,
                mockPatientResultRepository,
                mockAppointmentApiService
        );

        SecurityContextHolder.setContext(mockSecurityContext);
    }

    @Test
    void testRegisterUser() {
        // Arrange
        UserRegisterDTO registerDTO = new UserRegisterDTO();
        registerDTO.setEmail("test@example.com");
        registerDTO.setPassword("password");

        UserRoleEntity role = new UserRoleEntity();
        role.setRole(UserRoleEnum.PATIENT);

        when(mockUserRepository.count()).thenReturn(1L);
        when(mockUserRoleRepository.findByRole(UserRoleEnum.PATIENT)).thenReturn(role);
        when(mockPasswordEncoder.encode(registerDTO.getPassword())).thenReturn("encodedPassword");

        // Act
        toTest.registerUser(registerDTO);

        // Assert
        verify(mockUserRepository).save(userEntityCaptor.capture());
        UserEntity savedUser = userEntityCaptor.getValue();
        assertEquals("test@example.com", savedUser.getEmail());
        assertEquals("encodedPassword", savedUser.getPassword());
        assertEquals(1, savedUser.getRoles().size());
        assertEquals(UserRoleEnum.PATIENT, savedUser.getRoles().get(0).getRole());
    }

    @Test
    void testValidateCredentials() {
        // Arrange
        String email = "test@example.com";
        String password = "password";
        UserEntity user = new UserEntity();
        user.setPassword("encodedPassword");

        when(mockUserRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(mockPasswordEncoder.matches(password, "encodedPassword")).thenReturn(true);

        // Act
        boolean result = toTest.validateCredentials(email, password);

        // Assert
        assertTrue(result);
    }

    @Test
    void testGetUserProfile() {
        // Arrange
        String email = "test@example.com";
        UserEntity user = new UserEntity();
        user.setEmail(email);

        when(mockUserRepository.findByEmail(email)).thenReturn(Optional.of(user));

        // Act
        UserProfileDTO result = toTest.getUserProfile(email);

        // Assert
        assertEquals(email, result.getEmail());
    }

    @Test
    void testGetUserIdByEmail() {
        // Arrange
        String email = "test@example.com";
        UserEntity user = new UserEntity();
        user.setId(1L);

        when(mockUserRepository.findByEmail(email)).thenReturn(Optional.of(user));

        // Act
        Long result = toTest.getUserIdByEmail(email);

        // Assert
        assertEquals(1L, result);
    }

}