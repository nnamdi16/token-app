package com.nnamdi.generator.services.impl;

import com.nnamdi.generator.services.GeneratorService;

import java.util.Random;

public class GeneratorServiceImpl implements GeneratorService {
    @Override
    public String generateToken(String pin) {
     if (!pin.matches("[0-9]+")) {
         throw new IllegalArgumentException("Allowed digits must contain only numbers");
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
