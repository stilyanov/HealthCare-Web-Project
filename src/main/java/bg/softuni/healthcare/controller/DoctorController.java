package bg.softuni.healthcare.controller;

import bg.softuni.healthcare.service.impl.DoctorServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorServiceImpl doctorService;

    public DoctorController(DoctorServiceImpl doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/all")
    public String getDoctors() {
        return "all-doctors";
    }

    @GetMapping("/add")
    public String addDoctor() {
        return "add-doctor";
    }

}
