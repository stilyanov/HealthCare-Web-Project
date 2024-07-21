package bg.softuni.healthcare.exception;

import bg.softuni.healthcare.model.entity.User.HealthcareUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException ex, Model model) {
        // To show the full name of the currently logged in user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof HealthcareUserDetails userDetails) {
            model.addAttribute("fullName", userDetails.getFullName());
        }

        model.addAttribute("errorMessage", ex.getMessage());
        return "error-page";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNoHandlerFoundException(NoHandlerFoundException ex, Model model) {
        model.addAttribute("errorMessage", "Page not found!");
        return "error-page";
    }

}
