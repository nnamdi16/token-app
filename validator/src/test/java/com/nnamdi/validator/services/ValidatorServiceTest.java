package com.nnamdi.validator.services;

import com.nnamdi.validator.exceptions.BadRequestException;
import com.nnamdi.validator.services.impl.ValidatorServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@Slf4j
class ValidatorServiceTest {

    @Mock
    private ValidatorService validatorService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        validatorService = new ValidatorServiceImpl();
    }

    @Test
    void testToValidateValidToken() {
        String token = "2290-9009-2990-9099";
        final  var isValidToken = validatorService.validateToken(token);
        assertThat(isValidToken).isTrue();
    }

    @Test
    void testToValidateInValidToken() {
        String token = "6249-4472-0279-9421";
        assertThatThrownBy(() -> validatorService.validateToken(token))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("Token is invalid");
    }

    @Test
    void testToValidateTokenLessThanRequiredNumber() {
        String token = "6249-4472-0279";
        assertThatThrownBy(() -> validatorService.validateToken(token))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("Token is invalid");
    }




}
