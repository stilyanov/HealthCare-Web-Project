package bg.softuni.healthcare.service.impl;

import bg.softuni.healthcare.model.dto.user.UserProfileDTO;
import bg.softuni.healthcare.model.dto.user.UserRegisterDTO;
import bg.softuni.healthcare.model.entity.UserEntity;
import bg.softuni.healthcare.model.entity.UserRoleEntity;
import bg.softuni.healthcare.model.entity.enums.UserRoleEnum;
import bg.softuni.healthcare.repository.UserRepository;
import bg.softuni.healthcare.repository.UserRoleRepository;
import bg.softuni.healthcare.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }


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
    public UserEntity findById(Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public boolean checkEmail(String email) {
        return this.userRepository.findByEmail(email).isPresent();
    }

    @Override
    public UserProfileDTO getUserProfile(String email) {
        return this.userRepository.findByEmail(email)
                .map(user -> {
                    UserProfileDTO userProfileDTO = this.modelMapper.map(user, UserProfileDTO.class);
                    userProfileDTO.setRoles(user.getRoles());
                    return userProfileDTO;
                })
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public UserProfileDTO getUserProfileById(Long id) {
        return this.userRepository.findById(id)
                .map(user -> {
                    UserProfileDTO userProfileDTO = this.modelMapper.map(user, UserProfileDTO.class);
                    List<UserRoleEntity> roles = user.getRoles()
                            .stream()
//                            .map(role -> new UserRoleEntity(role.getRole()))
                            .toList();
//                    userProfileDTO.setRoles(roles);
                    return userProfileDTO;
                })
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}
