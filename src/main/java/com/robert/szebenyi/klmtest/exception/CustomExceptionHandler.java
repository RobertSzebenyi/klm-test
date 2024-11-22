package com.robert.szebenyi.klmtest.exception;

import com.robert.szebenyi.klmtest.exception.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomExceptionHandler {

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorDto onNotFoundException(NotFoundException e) {
        return ErrorDto.createFromException(e);
    }

    @ResponseBody
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ErrorDto onDataIntegrityViolationException(ValidationException e) {
        return ErrorDto.createFromException(e);
    }
}
