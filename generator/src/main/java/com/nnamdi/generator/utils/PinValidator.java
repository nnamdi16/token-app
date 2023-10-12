package com.nnamdi.generator.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PinValidator implements ConstraintValidator<ValidatePin, String> {

    @Override
    public void initialize(ValidatePin constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String pin, ConstraintValidatorContext constraintValidatorContext) {
        boolean allSame = false;
        char firstDigit = pin.charAt(0);

        for (int i = 1; i < pin.length(); i++) {
            if (pin.charAt(i) != firstDigit) {
                allSame = true;
                break;
            }
        }

        return allSame;
    }
}
