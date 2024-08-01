package bg.softuni.healthcare.controller;

import bg.softuni.healthcare.model.dto.ChangePasswordDTO;
import bg.softuni.healthcare.model.entity.DoctorEntity;
import bg.softuni.healthcare.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class PasswordChangeController {

    private final DoctorService doctorService;

    @GetMapping("/change-password")
    public String showChangePasswordPage(Model model) {
        model.addAttribute("changePasswordDTO", new ChangePasswordDTO());
        return "change-password";
    }

    @PutMapping("/change-password")
    public String changePassword(@Valid ChangePasswordDTO changePasswordDTO,
                                 Principal principal,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("changePasswordDTO", changePasswordDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.changePasswordDTO", bindingResult);
            return "redirect:/doctors/change-password";
        }

        DoctorEntity doctor = doctorService.findByEmail(principal.getName());
        doctorService.changePassword(doctor, changePasswordDTO.getPassword());

        return "redirect:/appointments/all";
    }

}
