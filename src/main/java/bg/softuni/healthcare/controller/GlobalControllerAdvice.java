package bg.softuni.healthcare.controller;

import bg.softuni.healthcare.model.dto.UserProfileDTO;
import bg.softuni.healthcare.model.entity.User.HealthcareUserDetails;
import bg.softuni.healthcare.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {

    private final UserService userService;

    @ModelAttribute
    public void globalAttributes(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails instanceof HealthcareUserDetails healthcareUserDetails) {
            model.addAttribute("fullName", healthcareUserDetails.getFullName());
        } else {
            model.addAttribute("fullName", "Anonymous");
        }
    }

    @ModelAttribute()
    public void userProfile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails != null) {
            UserProfileDTO userProfile = this.userService.getUserProfile(userDetails.getUsername());
            if (userProfile != null) {
                model.addAttribute("userId", userProfile.getId());
            }
        }
    }

}

