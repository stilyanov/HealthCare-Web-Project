package bg.softuni.healthcare.controller;

import bg.softuni.healthcare.model.dto.AddDoctorDTO;
import bg.softuni.healthcare.model.dto.ChangePasswordDTO;
import bg.softuni.healthcare.model.dto.DoctorDTO;
import bg.softuni.healthcare.model.dto.InfoDoctorDTO;
import bg.softuni.healthcare.model.entity.DoctorEntity;
import bg.softuni.healthcare.model.entity.enums.DepartmentEnum;
import bg.softuni.healthcare.service.DepartmentService;
import bg.softuni.healthcare.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    private final DepartmentService departmentService;
    private final ModelMapper modelMapper;

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
        List<DoctorDTO> allDoctors = this.doctorService.getAllDoctors();
        model.addAttribute("allDoctors", allDoctors);

        return "all-doctors";
    }

    @GetMapping("/info/{doctorId}")
    public String getDoctorInfo(@PathVariable Long doctorId, Model model) {
        InfoDoctorDTO doctorInfo = this.doctorService.getDoctorInfo(doctorId);
        model.addAttribute("doctorInfo", doctorInfo);
        return "doctor-info";
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

    @GetMapping("/find")
    public String findDoctor(@RequestParam(required = false) String department,
                             @RequestParam(required = false) String town,
                             @RequestParam(required = false) String name,
                             Model model) {
        model.addAttribute("departments", DepartmentEnum.values());
        model.addAttribute("towns", doctorService.getAllTowns());

        if (department != null && !department.isEmpty()) {
            model.addAttribute("doctors", departmentService.findByDepartment(DepartmentEnum.valueOf(department)));
            return "department-search";
        } else if (town != null && !town.isEmpty()) {
            model.addAttribute("doctors", departmentService.findByTown(town));
            return "town-search";
        } else if (name != null && !name.isEmpty()) {
            model.addAttribute("doctors", doctorService.findByName(name));
            model.addAttribute("searchName", name);
            return "name-search";
        } else {
            model.addAttribute("doctors", List.of());
        }
        return "find-doctor";
    }

    @GetMapping("/change-password")
    public String showChangePasswordPage(Model model) {
        model.addAttribute("changePasswordDTO", new ChangePasswordDTO());
        return "change-password";
    }

    @PostMapping("/change-password")
    public String changePassword(@Valid ChangePasswordDTO changePasswordDTO,
                                 Principal principal,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("changePasswordDTO", changePasswordDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.changePasswordDTO", bindingResult);
            return "redirect:/doctors/change-password";
        }

        DoctorEntity doctor = doctorService.findByEmail(principal.getName());
        doctorService.changePassword(doctor, changePasswordDTO.getPassword());

        return "redirect:/appointments/all";
    }

}
