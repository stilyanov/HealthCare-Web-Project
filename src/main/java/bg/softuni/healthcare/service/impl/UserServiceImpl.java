package bg.softuni.healthcare.service.impl;

import bg.softuni.healthcare.model.dto.UserRegisterDTO;
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

        UserRoleEntity byRole = userRoleRepository.findByRole(UserRoleEnum.ADMIN);

        Set<UserRoleEntity> roles = new HashSet<>();
        if (isFirstUser) {
            roles.add(userRoleRepository.findByRole(UserRoleEnum.ADMIN));
        } else {
            roles.add(userRoleRepository.findByRole(UserRoleEnum.PATIENT));
        }

        UserEntity user = this.modelMapper.map(registerDTO, UserEntity.class);

        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user.setRoles(roles);

        this.userRepository.save(user);
    }

    public boolean checkEmail(String email) {
        return this.userRepository.findByEmail(email).isPresent();
    }
}
