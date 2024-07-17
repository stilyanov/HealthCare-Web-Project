package bg.softuni.healthcare.validation.validator;

import bg.softuni.healthcare.model.dto.user.UserLoginDTO;
import bg.softuni.healthcare.service.UserService;
import bg.softuni.healthcare.validation.annotation.ValidateLogin;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class LoginValidator implements ConstraintValidator<ValidateLogin, UserLoginDTO> {

    private String message;

    private final UserService userService;

    public LoginValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(ValidateLogin constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(UserLoginDTO userLoginDTO, ConstraintValidatorContext constraintValidatorContext) {
        if (userLoginDTO.getEmail() == null || userLoginDTO.getPassword() == null) {
            return true;
        }

        boolean isValid = userService.validateCredentials(userLoginDTO.getEmail(), userLoginDTO.getPassword());

        if (!isValid) {
            constraintValidatorContext.unwrap(HibernateConstraintValidatorContext.class)
                    .buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return isValid;
    }
}
