package com.nnamdi.generator.controller;

import com.google.gson.Gson;
import com.nnamdi.generator.domain.dto.GenerateTokenRequestDto;
import com.nnamdi.generator.exceptions.BadRequestException;
import com.nnamdi.generator.services.GeneratorService;
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

import static com.nnamdi.generator.controller.BaseApiController.BASE_API_PATH;
import static com.nnamdi.generator.controller.BaseApiController.GENERATOR;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = GeneratorController.class)
class GeneratorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    GeneratorService generatorService;

    private  final String URL = BASE_API_PATH + GENERATOR;

    @Autowired
    Gson gson;

    String generateTokenRequest;

    @BeforeEach
    void setup() {
        generateTokenRequest = gson.toJson(requestDto());
    }

    @AfterEach
    void  tearDown () {
        Mockito.reset(generatorService);
    }


    @Test
    void generateToken() {
       when(generatorService.generateToken(String.valueOf(anyInt()))).thenReturn("2249-4472-0279-9420");
       try {
         mockMvc.perform(
               post(URL)
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(generateTokenRequest))
                 .andExpect(status().isOk())
                 .andDo(print());

       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }

    @Test
    void generateTokenShouldThrow() {
        when(generatorService.generateToken(anyString())).thenThrow(BadRequestException.class);
        try {
            mockMvc.perform(
                            post(URL)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(generateTokenRequest))
                    .andExpect(status().is4xxClientError())
                    .andDo(print());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    GenerateTokenRequestDto requestDto () {
        return  GenerateTokenRequestDto.builder()
                .pin("24790")
                .build();
    }
}
