package bg.softuni.healthcare.service;

import bg.softuni.healthcare.model.dto.user.UserProfileDTO;
import bg.softuni.healthcare.model.dto.user.UserRegisterDTO;
import bg.softuni.healthcare.model.entity.UserEntity;

public interface UserService {

    void registerUser(UserRegisterDTO registerDTO);

    UserEntity findById(Long id);

    boolean checkEmail(String email);

    UserProfileDTO getUserProfile(String email);

    UserProfileDTO getUserProfileById(Long id);
}
