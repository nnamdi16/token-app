package com.nnamdi.generator.services.impl;

import com.nnamdi.generator.exceptions.BadRequestException;
import com.nnamdi.generator.services.GeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class GeneratorServiceImpl implements GeneratorService {
     private final Random random = new Random();
    @Override
    public String generateToken(String pin) {
     if (!pin.matches("\\d+")) {
         throw new BadRequestException("pin must contain only numbers");
     }

     StringBuilder partialTokenBuilder = new StringBuilder();
     for (int index = 0; index < 15; index++) {
         int tokenIndex = this.random.nextInt(pin.length());
         char digit = pin.charAt(tokenIndex);
         partialTokenBuilder.append(digit);
     }
        return generateValidLuhnToken(partialTokenBuilder.toString()).replaceAll("(.{4})(?!$)", "$1-");
    }


    private String generateValidLuhnToken(String token) {
        int sum = 0;
        boolean doubleDigits = true;
        StringBuilder stringBuilder = new StringBuilder();
        for (int index = token.length() -1; index >= 0; index--) {
            char digitChar = token.charAt(index);
            int unitDigit = Character.getNumericValue(digitChar);
            if (doubleDigits) {
                unitDigit *=2;
                if (unitDigit > 9) {
                    unitDigit -= 9;
                }
            }

            sum += unitDigit;
            doubleDigits = !doubleDigits;
            stringBuilder.insert(0, digitChar);
        }
        int checkSum =  (10 - (sum % 10)) % 10;
        return stringBuilder.append(checkSum).toString();


    }
}
