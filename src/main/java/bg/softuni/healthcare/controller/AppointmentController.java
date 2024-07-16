package bg.softuni.healthcare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @GetMapping("/all")
    public String appointment() {
        return "appointment";
    }

    @GetMapping("/add")
    public String addAppointment() {

        return "add-appointment";
    }
}
