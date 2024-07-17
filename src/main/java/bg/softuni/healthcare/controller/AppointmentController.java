package bg.softuni.healthcare.controller;

import bg.softuni.healthcare.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/appointments")
@AllArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping("/all")
    public String appointment() {
        return "appointment";
    }

    @GetMapping("/find")
    public String findAppointment() {
        return "find-appointment";
    }
}
