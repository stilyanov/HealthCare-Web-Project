package bg.softuni.healthcare.controller;

import bg.softuni.healthcare.model.dto.appointment.DoctorAppointmentDTO;
import bg.softuni.healthcare.model.dto.patientResult.AddPatientResultDTO;
import bg.softuni.healthcare.service.PatientResultService;
import bg.softuni.healthcare.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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

    @GetMapping("/add/{patientId}")
    public String addPatientResult(@PathVariable Long patientId, Model model) {
        String patientFullName = userService.getUserFullNameById(patientId);
        model.addAttribute("patientFullName", patientFullName);
        model.addAttribute("patientId", patientId);
        return "add-patient-result";
    }

    @PostMapping("/add/{patientId}")
    public String doAddPatientResult(@PathVariable Long patientId,
                                     @Valid AddPatientResultDTO patientResultDTO,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addPatientResult", patientResultDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addPatientResult", bindingResult);
            return "redirect:/patient-result/add/" + patientId;
        }
        //TODO: Implement the logic for adding a patient result
        this.patientResultService.addPatientResult(patientResultDTO);
        return "redirect:/patient/all";
    }

}
