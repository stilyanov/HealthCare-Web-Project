package bg.softuni.healthcare.controller;

import bg.softuni.healthcare.model.entity.enums.DepartmentEnum;
import bg.softuni.healthcare.service.AppointmentService;
import bg.softuni.healthcare.service.DepartmentService;
import bg.softuni.healthcare.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/appointments")
@AllArgsConstructor
public class AppointmentController {

    @GetMapping("/all")
    public String appointment() {
        return "appointment";
    }

}
