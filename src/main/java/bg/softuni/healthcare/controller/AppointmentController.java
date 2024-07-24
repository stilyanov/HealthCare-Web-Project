package bg.softuni.healthcare.controller;

import bg.softuni.healthcare.model.dto.AppointmentDTO;
import bg.softuni.healthcare.model.dto.DoctorDTO;
import bg.softuni.healthcare.model.dto.UserAppointmentDTO;
import bg.softuni.healthcare.service.AppointmentService;
import bg.softuni.healthcare.service.DoctorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/appointments")
@AllArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final DoctorService doctorService;

    @GetMapping("/book/{doctorId}")
    public String appointment(@PathVariable("doctorId") Long doctorId,
                              Model model,
                              @RequestParam(value = "date", required = false)
                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        DoctorDTO doctor = doctorService.getDoctorById(doctorId);
        List<LocalDateTime> availableSlots = appointmentService.getAvailableAppointmentTimes(doctorId, date != null ? date : LocalDate.now());
        model.addAttribute("doctor", doctor);
        model.addAttribute("availableSlots", availableSlots);
        model.addAttribute("selectedDate", date != null ? date : LocalDate.now());
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setDoctorId(doctorId);
        model.addAttribute("appointment", appointmentDTO);
        return "book-appointment";
    }

    @PostMapping("/book/{doctorId}")
    public String bookAppointment(@Valid AppointmentDTO appointmentDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("appointment", appointmentDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.appointment", bindingResult);
            return "redirect:/appointments/book/" + appointmentDTO.getDoctorId();
        }
        appointmentService.bookAppointment(appointmentDTO);
        return "redirect:/appointments/all";
    }

    @GetMapping("/all")
    public String allAppointments(Model model) {
        List<UserAppointmentDTO> getUsersAppointments = appointmentService.getUsersAppointments();
        model.addAttribute("appointments", getUsersAppointments);
        return "appointments";
    }
}