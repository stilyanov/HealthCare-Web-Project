package bg.softuni.healthcare.controller;

import bg.softuni.healthcare.model.dto.UserProfileDTO;
import bg.softuni.healthcare.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile/{userId}")
    public String getProfile(@PathVariable Long userId, Model model) {
        UserProfileDTO userProfileDTO = this.userService.getUserProfileById(userId);
        model.addAttribute("userProfile", userProfileDTO);

        return "user-profile";
    }

}
