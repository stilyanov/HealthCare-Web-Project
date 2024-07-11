package bg.softuni.healthcare.controller;

import bg.softuni.healthcare.model.dto.UserLoginDTO;
import bg.softuni.healthcare.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class LoginController {

    @ModelAttribute("loginDTO")
    public UserLoginDTO loginDTO() {
        return new UserLoginDTO();
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login-error")
    public String loginUser(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("wrongCredentials", true);
        return "redirect:/users/login";
        //TODO! ADD FLASH ATTRIBUTE FOR WRONG CREDENTIALS
    }

}
