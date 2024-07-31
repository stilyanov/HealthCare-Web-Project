package bg.softuni.healthcare.service.impl;

import bg.softuni.healthcare.model.dto.UserProfileDTO;
import bg.softuni.healthcare.model.dto.UserRegisterDTO;
import bg.softuni.healthcare.model.entity.DoctorEntity;
import bg.softuni.healthcare.model.entity.UserEntity;
import bg.softuni.healthcare.model.entity.UserRoleEntity;
import bg.softuni.healthcare.model.entity.enums.UserRoleEnum;
import bg.softuni.healthcare.repository.DoctorRepository;
import bg.softuni.healthcare.repository.UserRepository;
import bg.softuni.healthcare.repository.UserRoleRepository;
import bg.softuni.healthcare.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;


    public void registerUser(UserRegisterDTO registerDTO) {
        boolean isFirstUser = this.userRepository.count() == 0;

        UserEntity user = this.modelMapper.map(registerDTO, UserEntity.class);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        List<UserRoleEntity> roles = new ArrayList<>();
        if (isFirstUser) {
            roles.add(userRoleRepository.findByRole(UserRoleEnum.ADMIN));
        } else {
            roles.add(userRoleRepository.findByRole(UserRoleEnum.PATIENT));
        }
        user.setRoles(roles);

        this.userRepository.save(user);
    }

    @Override
    public boolean validateCredentials(String email, String password) {
        Optional<UserEntity> userEntity = this.userRepository.findByEmail(email);
        return userEntity.isPresent() && passwordEncoder.matches(password, userEntity.get().getPassword());
    }

    @Override
    public UserEntity findById(Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public boolean isEmailUnique(String email) {
        return !this.userRepository.existsByEmail(email);
    }

    @Override
    public UserProfileDTO getUserProfile(String email) {
        return this.userRepository.findByEmail(email)
                .map(this::mapToUserProfileDTO)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public UserProfileDTO getUserProfileById(Long id) {
        return this.userRepository.findById(id)
                .map(this::mapToUserProfileDTO)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public List<UserProfileDTO> getAllUsers(UserEntity user) {
        return this.userRepository.findAll()
                .stream()
                .map(this::mapToUserProfileDTO)
                .toList();

    }

    @Override
    public void deleteUser(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities()
                .stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
        if (!isAdmin) {
            throw new IllegalArgumentException("You do not have permission to delete users");
        }

        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        boolean isDoctor = user.getRoles()
                .stream()
                .anyMatch(role -> role.getRole().equals(UserRoleEnum.DOCTOR));

        if (isDoctor) {
            DoctorEntity doctor = doctorRepository.findByEmail(user.getEmail())
                    .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));
            doctorRepository.deleteById(doctor.getId());
        }

        this.userRepository.deleteById(id);
    }

    @Override
    public Long getUserIdByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserEntity::getId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public String getUserRoleByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail)
                .map(user -> user.getRoles().getFirst().getRole().name())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    private UserProfileDTO mapToUserProfileDTO(UserEntity user) {
        UserProfileDTO userProfileDTO = this.modelMapper.map(user, UserProfileDTO.class);
        List<String> roles = user.getRoles()
                .stream()
                .map(roleEntity -> roleEntity.getRole().name())
                .toList();
        userProfileDTO.setRoles(roles);
        return userProfileDTO;
    }

}
