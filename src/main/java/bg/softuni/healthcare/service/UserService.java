package bg.softuni.healthcare.service;

import bg.softuni.healthcare.model.dto.user.UserProfileDTO;
import bg.softuni.healthcare.model.dto.user.UserRegisterDTO;
import bg.softuni.healthcare.model.entity.UserEntity;

import java.util.List;

public interface UserService {

    void registerUser(UserRegisterDTO registerDTO);

    boolean validateCredentials(String email, String password);

    UserEntity findById(Long id);

    boolean isEmailUnique(String email);

    UserProfileDTO getUserProfile(String email);

    UserProfileDTO getUserProfileById(Long id);

    List<UserProfileDTO> getAllUsers(UserEntity user);

    void deleteUser(Long id);

    Long getUserIdByEmail(String email);

    String getUserRoleByEmail(String userEmail);

    UserEntity findByEmail(String userEmail);

}
