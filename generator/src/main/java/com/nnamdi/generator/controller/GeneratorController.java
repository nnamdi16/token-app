package com.nnamdi.generator.controller;

import com.nnamdi.generator.domain.dto.GenerateTokenRequestDto;
import com.nnamdi.generator.domain.response.APIResponseMessages;
import com.nnamdi.generator.domain.response.AppResponse;
import com.nnamdi.generator.services.GeneratorService;
import com.nnamdi.generator.utils.AppUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.nnamdi.generator.controller.BaseApiController.BASE_API_PATH;
import static com.nnamdi.generator.controller.BaseApiController.GENERATOR;

@RestController
@Slf4j
@RequestMapping(BASE_API_PATH + GENERATOR)
@RequiredArgsConstructor
public class GeneratorController {
    private final GeneratorService generatorService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<AppResponse> generateToken(@RequestBody @Valid GenerateTokenRequestDto requestDto){
        AppResponse response = AppUtil.buildAppResponse(APIResponseMessages.SUCCESSFUL, true, generatorService.tokenGenerator(requestDto.getPin(), 16), null, HttpStatus.OK.value());
        return  ResponseEntity.ok(response);
    }
}
