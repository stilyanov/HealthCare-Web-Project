package bg.softuni.healthcare.controller;

import bg.softuni.healthcare.model.dto.user.UserLoginDTO;
import bg.softuni.healthcare.model.dto.user.UserRegisterDTO;
import bg.softuni.healthcare.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ModelAttribute("loginDTO")
    public UserLoginDTO loginDTO() {
        return new UserLoginDTO();
    }

    @ModelAttribute("registerDTO")
    public UserRegisterDTO registerDTO() {
        return new UserRegisterDTO();
    }

    @GetMapping("/profile/{userId}")
    public String getProfile(@PathVariable Long userId, Model model) {
        model.addAttribute("userProfile", userService.getUserProfileById(userId));
        return "user-profile";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login-error")
    public String loginError(RedirectAttributes redirectAttributes) {
        boolean wrongCredentials = true;
        redirectAttributes.addFlashAttribute("wrongCredentials", wrongCredentials);
        return "redirect:/users/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("registerDTO")) {
            model.addAttribute("registerDTO", new UserRegisterDTO());
        }
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
        userService.registerUser(registerDTO);
        return "redirect:/users/login";
    }


}