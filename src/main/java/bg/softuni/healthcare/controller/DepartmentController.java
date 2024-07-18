package bg.softuni.healthcare.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class DepartmentController {

    @GetMapping("/department")
    public String departments() {
        return "department";
    }

}
