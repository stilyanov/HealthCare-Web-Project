package bg.softuni.healthcare.controller;

import bg.softuni.healthcare.model.dto.user.UserRegisterDTO;
import bg.softuni.healthcare.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserServiceImpl userService;

    @ModelAttribute("registerDTO")
    public UserRegisterDTO registerDTO() {
        return new UserRegisterDTO();
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserRegisterDTO registerDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerDTO", registerDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerDTO", bindingResult);
            return "redirect:/users/register";
        }

        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("registerDTO", registerDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerDTO", bindingResult);
            redirectAttributes.addFlashAttribute("passwordDiff", "Passwords do not match!");
            // TODO: IF PASSWORD DOES NOT MATCH, DOES NOT APPEAR ON THE FRONT END
            return "redirect:/users/register";
        }

        if (userService.checkEmail(registerDTO.getEmail())) {
            redirectAttributes.addFlashAttribute("registerDTO", registerDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerDTO", bindingResult);
            redirectAttributes.addFlashAttribute("emailExists", "Email already exists");
            //TODO: EMAIL
            return "redirect:/users/register";
        }

        this.userService.registerUser(registerDTO);
        return "redirect:/users/login";
    }

}
