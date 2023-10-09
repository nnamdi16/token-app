package com.nnamdi.generator.exceptions;

import com.nnamdi.generator.domain.response.AppResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ExceptionHandlers {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public AppResponse handleBadRequest (final BadRequestException ex) {
        return AppResponse.builder()
                .data(null)
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .success(false)
                .build();
    }
}
