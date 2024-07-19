package bg.softuni.healthcare.controller;

import bg.softuni.healthcare.model.dto.DoctorDTO;
import bg.softuni.healthcare.model.dto.UserProfileDTO;
import bg.softuni.healthcare.model.entity.UserEntity;
import bg.softuni.healthcare.service.DoctorService;
import bg.softuni.healthcare.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final DoctorService doctorService;

    @GetMapping("/panel")
    public String getAdminPanel(Model model, UserEntity user) {
        List<UserProfileDTO> allUsers = this.userService.getAllUsers(user);
        List<DoctorDTO> allDoctors = this.doctorService.getAllDoctors();

        model.addAttribute("allDoctors", allDoctors);
        model.addAttribute("allUsers", allUsers);

        return "admin-panel";
    }


    @DeleteMapping("/delete/user/{id}")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        this.userService.deleteUser(id);
        redirectAttributes.addFlashAttribute("success", "User with ID " + id + " was deleted successfully!");

        return "redirect:/admin/panel";
    }

    @DeleteMapping("/delete/doctor/{id}")
    public String deleteDoctor(@PathVariable Long id, RedirectAttributes redirectAttributes){
        this.doctorService.deleteDoctor(id);
        redirectAttributes.addFlashAttribute("success", "Doctor with ID " + id + " was deleted successfully!");

        return "redirect:/admin/panel";
    }

}
