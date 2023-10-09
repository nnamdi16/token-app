package com.nnamdi.validator.services;

import com.nnamdi.validator.domain.request.ValidateTokenRequestDto;
import com.nnamdi.validator.services.impl.ValidatorServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
        String token = "2249-4472-0279-9420";
        final  var isValidToken = validatorService.validateToken(token);
        assertThat(isValidToken).isTrue();
    }

    @Test
    void testToValidateInValidToken() {
        String token = "6249-4472-0279-9421";
        final  var isValidToken = validatorService.validateToken(token);
        assertThat(isValidToken).isFalse();
    }




}
