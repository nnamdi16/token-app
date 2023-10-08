package com.nnamdi.generator.service;


import com.nnamdi.generator.domain.dto.GenerateTokenRequestDto;
import com.nnamdi.generator.services.GeneratorService;
import com.nnamdi.generator.services.impl.GeneratorServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
    void testToGenerateTokenContainsPin() {
        GenerateTokenRequestDto generateTokenRequestDto = buildGenerateTokenRequestDto();
        final  var token = generatorService.generateToken(generateTokenRequestDto.getPin());
        assertThat(token).contains(generateTokenRequestDto.getPin());
    }
    GenerateTokenRequestDto buildGenerateTokenRequestDto () {
        return  GenerateTokenRequestDto.builder().pin("24790").build();
    }
}
