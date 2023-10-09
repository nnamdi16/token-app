package com.nnamdi.generator.service;


import com.nnamdi.generator.domain.dto.GenerateTokenRequestDto;import com.nnamdi.generator.exceptions.BadRequestException;
import com.nnamdi.generator.services.GeneratorService;
import com.nnamdi.generator.services.impl.GeneratorServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@Slf4j
public class GeneratorServiceTest {

    @Mock
    private GeneratorService generatorService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        generatorService = new GeneratorServiceImpl();
    }


    @Test
    void testToGenerateToken() {
        GenerateTokenRequestDto generateTokenRequestDto = buildGenerateTokenRequestDto();
        final  var token = generatorService.generateToken(generateTokenRequestDto.getPin());
        System.out.println(token);
        assertThat(token).isNotNull();
        assertThat(token.length()).isEqualTo(19);

    }

    @Test
    void testToGenerateTokenWithRequiredDigits() {
        GenerateTokenRequestDto generateTokenRequestDto = buildGenerateTokenRequestDto();
        final  var token = generatorService.generateToken(generateTokenRequestDto.getPin());
        final var digits = token.replaceAll("-", "");
        assertThat(digits).isNotNull();
        assertThat(digits).hasSize(16);
    }

    @Test
    void testShowThrowIfPinIsNotNumbers() {
        final  var pin = "str123";
        assertThatThrownBy(() -> generatorService.generateToken(pin))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("pin must contain only numbers");
    }

//    @Test
//    void testToGenerateTokenContainsPin() {
//        GenerateTokenRequestDto generateTokenRequestDto = buildGenerateTokenRequestDto();
//        final  var token = generatorService.generateToken(generateTokenRequestDto.getPin());
//        System.out.println(token);
//        System.out.println(generateTokenRequestDto.getPin());
//        assertThat(isSubset(generateTokenRequestDto.getPin(), token)).isTrue();
//    }
    GenerateTokenRequestDto buildGenerateTokenRequestDto () {
        return  GenerateTokenRequestDto.builder().pin("635951234").build();
    }

//    private boolean isSubset(String subset, String pattern) {
//        boolean data = pattern.contains(subset);
//        System.out.println(data);
//        return data;
//    }
}
