package bg.softuni.healthcare.controller;

import bg.softuni.healthcare.model.dto.patientResult.AddPatientResultDTO;
import bg.softuni.healthcare.model.entity.UserEntity;
import bg.softuni.healthcare.service.PatientResultService;
import bg.softuni.healthcare.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/patient-result")
@RequiredArgsConstructor
public class PatientResultController {

    private final PatientResultService patientResultService;
    private final UserService userService;

    @ModelAttribute("addPatientResult")
    public AddPatientResultDTO addPatientResultDTO() {
        return new AddPatientResultDTO();
    }

    @GetMapping("/add/{patientId}/{appointmentId}")
    public String addPatientResult(@PathVariable Long patientId,
                                   @PathVariable Long appointmentId,
                                   Model model) {
        String patientFullName = userService.getUserFullNameById(patientId);
        model.addAttribute("patientFullName", patientFullName);
        model.addAttribute("patientId", patientId);
        model.addAttribute("appointmentId", appointmentId);
        return "add-patient-result";
    }

    @PostMapping("/add/{patientId}/{appointmentId}")
    public String doAddPatientResult(@PathVariable Long patientId,
                                     @PathVariable Long appointmentId,
                                     @Valid AddPatientResultDTO patientResultDTO,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addPatientResult", patientResultDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addPatientResult", bindingResult);
            return "redirect:/patient-result/add/" + patientId + "/" + appointmentId;
        }

        this.patientResultService.addPatientResult(patientResultDTO);
        String patientFullName = userService.getUserFullNameById(patientId);
        redirectAttributes.addFlashAttribute("successMessage", "Send results to " + patientFullName + " successfully!");
        return "redirect:/appointments/all";
    }

}
