package com.nnamdi.validator.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TokenFormatValidator.class)
public @interface ValidateTokenFormat {
    String message() default "Invalid token format. It should be XXXX-XXXX-XXXX-XXXX.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
