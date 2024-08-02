package bg.softuni.healthcare.controller;

import bg.softuni.healthcare.model.dto.appointment.DoctorAppointmentDTO;
import bg.softuni.healthcare.model.dto.patientResult.AddPatientResultDTO;
import bg.softuni.healthcare.service.PatientResultService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/patient-result")
@RequiredArgsConstructor
public class PatientResultController {

    private final PatientResultService patientResultService;

    @GetMapping("/all")
    public String getAllPatientResult(Model model) {
        return "all-patient-result";
    }

    @GetMapping
    public String listAppointments

    @GetMapping("/add")
    public String addPatientResult(Model model) {
        List<DoctorAppointmentDTO> appointments = patientResultService.getAppointmentsForCurrentDoctor();
        AddPatientResultDTO addPatientResultDTO = new AddPatientResultDTO();
        addPatientResultDTO.setAppointments(appointments);
        model.addAttribute("appointments", appointments);
        return "add-patient-result";
    }

    @PostMapping("/add")
    public String doAddPatientResult(@Valid AddPatientResultDTO patientResultDTO,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addPatientResult", patientResultDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addPatientResult", bindingResult);
            return "redirect:/patient-result/add";
        }

        this.patientResultService.addPatientResult(patientResultDTO);
        return "redirect:/patient/all";
    }

}
