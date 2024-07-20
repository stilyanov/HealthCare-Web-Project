package bg.softuni.healthcare.controller;

import bg.softuni.healthcare.model.dto.AppointmentDTO;
import bg.softuni.healthcare.model.dto.DoctorDTO;
import bg.softuni.healthcare.service.AppointmentService;
import bg.softuni.healthcare.service.DoctorService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/appointments")
@AllArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final DoctorService doctorService;
    private final ModelMapper modelMapper;

    @GetMapping("/book/{doctorId}")
    public String appointment(@PathVariable("doctorId") Long doctorId, Model model) {
        DoctorDTO doctor = this.doctorService.getDoctorById(doctorId);
        model.addAttribute("doctor", doctor);
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setDoctorId(doctorId);
        model.addAttribute("appointment", appointmentDTO);
        return "book-appointment";
    }



}
