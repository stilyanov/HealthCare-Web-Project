package bg.softuni.healthcare.controller;

import bg.softuni.healthcare.model.dto.AppointmentDTO;
import bg.softuni.healthcare.model.dto.DoctorDTO;
import bg.softuni.healthcare.service.AppointmentService;
import bg.softuni.healthcare.service.DoctorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/appointments")
@AllArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final DoctorService doctorService;
    private final ModelMapper modelMapper;

    @ModelAttribute("appointment")
    public AppointmentDTO appointmentDTO() {
        return new AppointmentDTO();
    }

    @GetMapping("/book/{doctorId}")
    public String appointment(@PathVariable("doctorId") Long doctorId, Model model, AppointmentDTO appointmentDTO) {
        DoctorDTO doctor = this.doctorService.getDoctorById(doctorId);
        model.addAttribute("doctor", doctor);
        appointmentDTO.setDoctorId(doctorId);
        model.addAttribute("appointment", appointmentDTO);
        return "book-appointment";
    }

    @PostMapping("/book/{doctorId}")
    public String bookAppointment(@Valid AppointmentDTO appointmentDTO,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("appointment", appointmentDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.appointment", bindingResult);
            return "redirect:/appointments/book/" + appointmentDTO.getDoctorId();
        }

        this.appointmentService.bookAppointment(appointmentDTO);

        return "redirect:/";
    }

    @GetMapping("/all")
    public String allAppointments(Model model) {
        model.addAttribute("appointments", this.appointmentService.getAllAppointments());
        return "all-appointments";
    }

}
