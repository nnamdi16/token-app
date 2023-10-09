package com.nnamdi.validator.services.impl;

import com.nnamdi.validator.exceptions.BadRequestException;
import com.nnamdi.validator.services.ValidatorService;
import org.springframework.stereotype.Service;

@Service
public class ValidatorServiceImpl implements ValidatorService {
    @Override
    public boolean validateToken(String token) {
        String reverseString = new StringBuilder(token.replace("-", "")).reverse().toString();
        int sum = 0;
        boolean doubleDigits = false;
        for (int index = 0; index < reverseString.length(); index++) {
            int unitDigit = Character.getNumericValue(reverseString.charAt(index));
            if (doubleDigits) {
                unitDigit *= 2;
                if (unitDigit > 9) {
                    unitDigit -= 9;
                }

            }
            sum += unitDigit;
            doubleDigits = !doubleDigits;

        }
        if (sum % 10 != 0) {
            throw new BadRequestException("Token is invalid");
        }
        return true;
    }
}
