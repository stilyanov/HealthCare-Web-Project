package bg.softuni.healthcare.controller;

import bg.softuni.healthcare.model.dto.appointment.AddAppointmentDTO;
import bg.softuni.healthcare.model.dto.appointment.DoctorAppointmentDTO;
import bg.softuni.healthcare.model.dto.doctor.DoctorDTO;
import bg.softuni.healthcare.model.dto.user.UserAppointmentDTO;
import bg.softuni.healthcare.model.entity.enums.DepartmentEnum;
import bg.softuni.healthcare.service.DepartmentService;
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

    @ModelAttribute("appointment")
    public AddAppointmentDTO appointmentDTO() {
        return new AddAppointmentDTO();
    }

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

    @GetMapping("/all")
    public String userAppointments(Model model, @RequestParam(value = "message", required = false) String message) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        Long userId = userService.getUserIdByEmail(userEmail);

        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return "redirect:/admin/panel";
        }

        String userRole = userService.getUserRoleByEmail(userEmail);
        if ("PATIENT".equals(userRole)) {
            List<UserAppointmentDTO> userAppointments = appointmentService.getAppointmentsByPatientId(userId);
            model.addAttribute("appointments", userAppointments);
            model.addAttribute("role", "patient");
        } else {
            Long doctorId = doctorService.getDoctorIdByEmail(userEmail);
            List<DoctorAppointmentDTO> doctorAppointments = appointmentService.getFutureAppointmentsByDoctorId(doctorId);
            List<DoctorAppointmentDTO> pastAppointments = appointmentService.getPastAppointmentsByDoctorId(doctorId);
            model.addAttribute("appointments", doctorAppointments);
            model.addAttribute("pastAppointments", pastAppointments);
            model.addAttribute("role", "doctor");
        }

        if (message != null) {
            model.addAttribute("message", message);
        }

            return "appointments";
    }

    @PostMapping("/book/{doctorId}")
    public String bookAppointment(@Valid AddAppointmentDTO appointmentDTO,
                                  @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("appointment", appointmentDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.appointment", bindingResult);
            String redirectUrl = "redirect:/appointments/book/" + appointmentDTO.getDoctorId();
            if (date != null) {
                redirectUrl += "?date=" + date;
            }
            return redirectUrl;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        Long patientId = userService.getUserIdByEmail(userEmail);

        DoctorDTO doctor = doctorService.getDoctorById(appointmentDTO.getDoctorId());
        DepartmentEnum department = doctor.getDepartment();
        appointmentDTO.setDepartment(department);
        appointmentDTO.setPatientId(patientId);

        appointmentService.bookAppointment(appointmentDTO);
        return "redirect:/appointments/all";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        appointmentService.deleteAppointment(id);
        redirectAttributes.addFlashAttribute("message", "Appointment with " + id + " was deleted successfully!");
        return "redirect:/appointments/all";
    }
}