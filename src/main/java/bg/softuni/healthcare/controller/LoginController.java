package bg.softuni.healthcare.controller;

import bg.softuni.healthcare.model.dto.user.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class LoginController {

    @ModelAttribute("loginDTO")
    public UserDTO loginDTO() {
        return new UserDTO();
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login-error")
    public String loginError(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("wrongCredentials", true);
        return "redirect:/users/login";
        //TODO! ADD FLASH ATTRIBUTE FOR WRONG CREDENTIALS
    }

}
