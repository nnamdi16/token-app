package com.nnamdi.generator.services;

public interface GeneratorService {
    String generateToken(String pin);
    String tokenGenerator(String pin, int tokenLength);

}
