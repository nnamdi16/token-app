package com.nnamdi.generator.services.impl;

import com.nnamdi.generator.exceptions.BadRequestException;
import com.nnamdi.generator.services.GeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Override
    public String tokenGenerator(String pin, int tokenLength) {
        int[] checkArray = new int[3];

        var cardNum = new int[4];

        for (int d = 2; d >= 0; d--)
        {
            cardNum[d] = random.nextInt(0, 9);
            checkArray[d] = ( cardNum[d] * (((d+1)%2)+1)) % 9;
        }

        cardNum[3] = ( Arrays.stream(checkArray).sum() * 9 ) % 10;

        var sb = new StringBuilder();

        for (int d = 0; d < 4; d++)
        {
            sb.append(cardNum[d]);
        }
        return sb.toString();

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
        int checkSum =  (sum % 10) == 0 ? 0 : 10 - (sum % 10);

        return stringBuilder.insert(0, checkSum).toString();


    }
}
