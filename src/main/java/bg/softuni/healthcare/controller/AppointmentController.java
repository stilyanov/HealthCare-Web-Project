package bg.softuni.healthcare.controller;

import bg.softuni.healthcare.model.dto.AddAppointmentDTO;
import bg.softuni.healthcare.model.dto.DoctorDTO;
import bg.softuni.healthcare.model.dto.FullAppointmentsInfoDTO;
import bg.softuni.healthcare.model.dto.UserAppointmentDTO;
import bg.softuni.healthcare.service.DoctorService;
import bg.softuni.healthcare.service.UserService;
import bg.softuni.healthcare.service.impl.AppointmentApiServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private final AppointmentApiServiceImpl appointmentService;
    private final DoctorService doctorService;
    private final UserService userService;

    @GetMapping("/book/{doctorId}")
    public String appointment(@PathVariable("doctorId") Long doctorId,
                              Model model,
                              @RequestParam(value = "date", required = false)
                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        DoctorDTO doctor = doctorService.getDoctorById(doctorId);
        LocalDate appointmentDate = (date != null) ? date : LocalDate.now();
        List<LocalDateTime> availableSlots = appointmentService.getAvailableAppointmentTimes(doctorId, appointmentDate);

        model.addAttribute("doctor", doctor);
        model.addAttribute("availableSlots", availableSlots);
        model.addAttribute("selectedDate", appointmentDate);

        AddAppointmentDTO appointmentDTO = new AddAppointmentDTO();
        appointmentDTO.setDoctorId(doctorId);
        model.addAttribute("appointment", appointmentDTO);

        return "book-appointment";
    }

    @GetMapping("/user")
    public String userAppointments (Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        Long userId = userService.getUserIdByEmail(userEmail);

        List<UserAppointmentDTO> userAppointments = appointmentService.getUserAppointments(userId);
        model.addAttribute("appointments", userAppointments);
        return "appointments";
    }


    @PostMapping("/book/{doctorId}")
    public String bookAppointment(@Valid AddAppointmentDTO appointmentDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("appointment", appointmentDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.appointment", bindingResult);
            return "redirect:/appointments/book/" + appointmentDTO.getDoctorId();
        }
        appointmentService.bookAppointment(appointmentDTO);
        return "redirect:/appointments/all";
    }
}