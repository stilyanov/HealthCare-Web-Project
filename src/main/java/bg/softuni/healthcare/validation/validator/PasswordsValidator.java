package bg.softuni.healthcare.validation.validator;

import bg.softuni.healthcare.model.dto.doctor.ChangePasswordDTO;
import bg.softuni.healthcare.model.dto.user.UserRegisterDTO;
import bg.softuni.healthcare.validation.annotation.ValidatePasswords;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class PasswordsValidator implements ConstraintValidator<ValidatePasswords, Object> {

    private String message;

    @Override
    public void initialize(ValidatePasswords constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        String password = null;
        String confirmPassword = null;

        if (obj instanceof UserRegisterDTO registerDTO) {
            password = registerDTO.getPassword();
            confirmPassword = registerDTO.getConfirmPassword();
        } else if (obj instanceof ChangePasswordDTO changePasswordDTO) {
            password = changePasswordDTO.getPassword();
            confirmPassword = changePasswordDTO.getConfirmPassword();
        }

        if (password == null || confirmPassword == null) {
            return true;
        }

        boolean isValid = password.equals(confirmPassword);

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
