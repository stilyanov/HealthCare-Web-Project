package bg.softuni.healthcare.interceptor;

import bg.softuni.healthcare.model.entity.DoctorEntity;
import bg.softuni.healthcare.service.DoctorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.security.Principal;

@Component
@RequiredArgsConstructor
public class PasswordChangeInterceptor implements HandlerInterceptor {

    private final DoctorService doctorService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            try {
                DoctorEntity doctor = doctorService.findByEmail(principal.getName());

                if (doctor != null) {
                    if (!doctor.isPasswordChanged() && !request.getRequestURI().contains("/change-password")) {
                        response.sendRedirect("/doctors/change-password");
                        return false;
                    }
                }
            } catch (IllegalArgumentException e) {
                return true;
            }
        }
        return true;
    }

}
