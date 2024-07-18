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

    private final AppointmentService appointmentService;
    private final DepartmentService departmentService;
    private final DoctorService doctorService;

    @GetMapping("/all")
    public String appointment() {
        return "appointment";
    }

    @GetMapping("/find")
    public String findAppointment(@RequestParam(required = false) String department,
                                  @RequestParam(required = false) String town,
                                  @RequestParam(required = false) String name,
                                  Model model) {
        model.addAttribute("departments", DepartmentEnum.values());

        if (department != null && !department.isEmpty()) {
            model.addAttribute("doctors", departmentService.findByDepartment(DepartmentEnum.valueOf(department)));
            return "department";
        } else if (town != null && !town.isEmpty()) {
//            model.addAttribute("appointments", doctorService.findByTown(town));
        } else if (name != null && !name.isEmpty()) {
//            model.addAttribute("appointments", doctorService.findByName(name));
        }
        return "find-appointment";
    }
}
