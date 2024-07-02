package pl.coderslab.validation;

import lombok.Data;

import javax.validation.ConstraintViolation;

@Data
public class ValidationViolationDTO {
    private final String validatedClass;
    private final String property;
    private final String message;

    public static ValidationViolationDTO of(Class<?> clazz, ConstraintViolation<?> violation) {
        return new ValidationViolationDTO(
                clazz.getSimpleName(),
                violation.getPropertyPath().toString(),
                violation.getMessage()
        );
    }
}
