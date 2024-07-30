package bg.softuni.healthcare.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            model.addAttribute("statusCode", statusCode);
            HttpStatus httpStatus = HttpStatus.resolve(statusCode);
            if (httpStatus != null) {
                model.addAttribute("statusMessage", httpStatus.getReasonPhrase());
                model.addAttribute("errorMessage", statusCode + " " + httpStatus.getReasonPhrase());
            } else {
                model.addAttribute("errorMessage", "Page not found!");
            }
        }
        return "error-page";
    }

    public String getErrorPath() {
        return "/error";
    }
}