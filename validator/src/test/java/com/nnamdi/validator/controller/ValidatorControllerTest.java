package com.nnamdi.validator.controller;

import com.google.gson.Gson;
import com.nnamdi.validator.domain.request.ValidateTokenRequestDto;
import com.nnamdi.validator.services.ValidatorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.nnamdi.validator.controller.BaseApiController.BASE_API_PATH;
import static com.nnamdi.validator.controller.BaseApiController.VALIDATOR;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ValidatorController.class)
class ValidatorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ValidatorService validatorService;

    @Autowired
    Gson gson;

    String tokenRequest;


    @BeforeEach
    void  setup() {
        tokenRequest = gson.toJson(requestDto());
    }

    @AfterEach
    void  tearDown() {
        Mockito.reset(validatorService);
    }


    @Test
    void validateToken() {
        when(validatorService.validateToken(anyString())).thenReturn(true);
        try {
            String URL = BASE_API_PATH + VALIDATOR;
            mockMvc.perform(
                    get(URL)
                            .param("token", requestDto().getToken())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andDo(print());
        }catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }

    ValidateTokenRequestDto requestDto() {
        return  ValidateTokenRequestDto.builder()
                .token("9472-2990-4294-2274")
                .build();
    }


}
