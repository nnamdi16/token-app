package com.nnamdi.generator.exceptions;

import com.nnamdi.generator.domain.response.AppResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public AppResponse processValidationError(MethodArgumentNotValidException ex) {

        BindingResult result = ex.getBindingResult();

        FieldError error = result.getFieldError();

        assert error != null;
        return processFieldError(error);
    }


    private AppResponse processFieldError(FieldError error) {

        return AppResponse.builder()
                .error(error.getDefaultMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(error.getDefaultMessage())
                .build();
    }

}
