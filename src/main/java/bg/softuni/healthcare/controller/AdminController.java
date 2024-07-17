package bg.softuni.healthcare.controller;

import bg.softuni.healthcare.model.dto.UserProfileDTO;
import bg.softuni.healthcare.model.entity.UserEntity;
import bg.softuni.healthcare.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping("/admin")
    public String getAdminPanel(Model model, UserEntity user) {
        List<UserProfileDTO> userProfileDTO = this.userService.getAllUsers(user);
        model.addAttribute("allUsers", userProfileDTO);

        return "admin-panel";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        this.userService.deleteUser(id);
        redirectAttributes.addFlashAttribute("success", "User with ID " + id + " was deleted successfully!");

        return "redirect:/users/admin";
    }
}
