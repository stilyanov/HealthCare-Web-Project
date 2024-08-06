package bg.softuni.healthcare.controller;

import bg.softuni.healthcare.model.dto.ContactDTO;
import bg.softuni.healthcare.model.dto.appointment.FullAppointmentsInfoDTO;
import bg.softuni.healthcare.model.dto.user.UserProfileDTO;
import bg.softuni.healthcare.model.entity.UserEntity;
import bg.softuni.healthcare.service.*;
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
    private final AppointmentApiService appointmentService;
    private final ContactService contactService;

    @GetMapping("/panel")
    public String getAdminPanel(Model model, UserEntity user) {
        List<UserProfileDTO> allUsers = this.userService.getAllUsers(user);
        List<UserProfileDTO> patientUsers = allUsers
                .stream()
                .filter(u -> u.getRoles().contains("PATIENT"))
                .toList();
        List<UserProfileDTO> doctorUsers = allUsers
                .stream()
                .filter(u -> u.getRoles().contains("DOCTOR"))
                .toList();
        List<FullAppointmentsInfoDTO> allAppointments = this.appointmentService.getAllFullAppointmentsInfo();
        List<ContactDTO> allContacts = this.contactService.getAllContacts();

        model.addAttribute("patientUsers", patientUsers);
        model.addAttribute("doctorUsers", doctorUsers);
        model.addAttribute("allAppointments", allAppointments);
        model.addAttribute("allContacts", allContacts);

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
        this.userService.deleteUser(id);
        redirectAttributes.addFlashAttribute("success", "Doctor with ID " + id + " was deleted successfully!");

        return "redirect:/admin/panel";
    }

    @DeleteMapping("/delete/appointment/{id}")
    public String deleteAppointment(@PathVariable Long id, RedirectAttributes redirectAttributes){
        this.appointmentService.deleteAppointment(id);
        redirectAttributes.addFlashAttribute("success", "Appointment with ID " + id + " was deleted successfully!");

        return "redirect:/admin/panel";
    }

    @DeleteMapping("/delete/contact/{id}")
    public String deleteContact(@PathVariable Long id, RedirectAttributes redirectAttributes){
        this.contactService.deleteContact(id);
        redirectAttributes.addFlashAttribute("success", "Contact with ID " + id + " was deleted successfully!");

        return "redirect:/admin/panel";
    }

}
