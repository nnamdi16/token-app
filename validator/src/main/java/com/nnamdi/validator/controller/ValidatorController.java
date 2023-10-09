package com.nnamdi.validator.controller;

import com.nnamdi.validator.domain.response.APIResponseMessages;
import com.nnamdi.validator.domain.response.AppResponse;
import com.nnamdi.validator.services.ValidatorService;
import com.nnamdi.validator.utils.AppUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.nnamdi.validator.controller.BaseApiController.BASE_API_PATH;
import static com.nnamdi.validator.controller.BaseApiController.VALIDATOR;

@RestController
@Slf4j
@RequestMapping(BASE_API_PATH + VALIDATOR)
@RequiredArgsConstructor
public class ValidatorController {
    private final ValidatorService validatorService;


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{token}")
    public ResponseEntity<AppResponse> validateToken(@PathVariable("token") String token){
        AppResponse response = AppUtil.buildAppResponse(APIResponseMessages.SUCCESSFUL, true,validatorService.validateToken(token), null, HttpStatus.OK.value());
        return  ResponseEntity.ok(response);
    }
}
