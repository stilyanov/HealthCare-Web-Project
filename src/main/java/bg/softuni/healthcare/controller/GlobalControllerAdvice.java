package bg.softuni.healthcare.controller;

import bg.softuni.healthcare.model.entity.User.HealthcareUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute
    public void globalAttributes(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails instanceof HealthcareUserDetails healthcareUserDetails) {
            model.addAttribute("fullName", healthcareUserDetails.getFullName());
        } else {
            model.addAttribute("fullName", "Anonymous");
        }
    }
}

