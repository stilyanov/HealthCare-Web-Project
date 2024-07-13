package bg.softuni.healthcare.controller;

import bg.softuni.healthcare.model.dto.doctor.AddDoctorDTO;
import bg.softuni.healthcare.model.dto.doctor.AllDoctorsDTO;
import bg.softuni.healthcare.model.entity.enums.DepartmentEnum;
import bg.softuni.healthcare.service.impl.DoctorServiceImpl;
import jakarta.validation.Valid;
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
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorServiceImpl doctorService;

    public DoctorController(DoctorServiceImpl doctorService) {
        this.doctorService = doctorService;
    }

    @ModelAttribute("addDoctor")
    public AddDoctorDTO addDoctorDTO() {
        return new AddDoctorDTO();
    }

    @ModelAttribute("departments")
    public DepartmentEnum[] departments() {
        return DepartmentEnum.values();
    }

    @GetMapping("/all")
    public String getDoctors(Model model) {
        List<AllDoctorsDTO> allDoctors = this.doctorService.getAllDoctors();
        model.addAttribute("allDoctors", allDoctors);

        return "all-doctors";
    }

    @GetMapping("/add")
    public String addDoctor() {
        return "add-doctor";
    }

    @PostMapping("/add")
    public String doAddDoctor(@Valid AddDoctorDTO addDoctorDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addDoctor", addDoctorDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addDoctor", bindingResult);
            return "redirect:/doctors/add";
        }

        this.doctorService.addDoctor(addDoctorDTO);

        return "redirect:/doctors/all";
    }

}
