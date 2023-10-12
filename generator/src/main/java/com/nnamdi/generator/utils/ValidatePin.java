package com.nnamdi.generator.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PinValidator.class)
public @interface ValidatePin {
    String message() default "Wrong pin format. All digits should not be the same";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {

    };
}
