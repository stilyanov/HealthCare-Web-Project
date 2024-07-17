package bg.softuni.healthcare.validation.validator;

import bg.softuni.healthcare.model.dto.UserRegisterDTO;
import bg.softuni.healthcare.validation.annotation.ValidatePasswords;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class PasswordsValidator implements ConstraintValidator<ValidatePasswords, UserRegisterDTO> {

    private String message;

    @Override
    public void initialize(ValidatePasswords constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(UserRegisterDTO registerDTO, ConstraintValidatorContext constraintValidatorContext) {
        if (registerDTO.getPassword() == null || registerDTO.getConfirmPassword() == null) {
            return true;
        }

        boolean isValid = registerDTO.getPassword().equals(registerDTO.getConfirmPassword());

        if (!isValid) {
            constraintValidatorContext.unwrap(HibernateConstraintValidatorContext.class)
                    .buildConstraintViolationWithTemplate(message)
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return isValid;
    }
}
