package com.nnamdi.generator.services.impl;

import com.nnamdi.generator.exceptions.BadRequestException;
import com.nnamdi.generator.services.GeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class GeneratorServiceImpl implements GeneratorService {
    @Override
    public String generateToken(String pin) {
     if (!pin.matches("[0-9]+")) {
         throw new BadRequestException("pin must contain only numbers");
     }

     StringBuilder tokenBuilder = new StringBuilder();
     Random random = new Random();
     for (int index = 0; index < 16; index++) {
         int tokenIndex = random.nextInt(pin.length());
         char digit = pin.charAt(tokenIndex);
         tokenBuilder.append(digit);
     }
     return  tokenBuilder.toString().replaceAll("(.{4})(?!$)", "$1-");
    }
}
