package com.nnamdi.validator.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TokenFormatValidator implements ConstraintValidator<ValidateTokenFormat, String> {

    private static final String TOKEN_REGEX = "\\d{4}-\\d{4}-\\d{4}-\\d{4}";
    @Override
    public void initialize(ValidateTokenFormat constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String token, ConstraintValidatorContext constraintValidatorContext) {
        return token != null && token.matches(TOKEN_REGEX);
    }
}
