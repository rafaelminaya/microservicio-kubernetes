package org.rminaya.springcloud.msvc.usuarios.controllers.error_handler;

import org.rminaya.springcloud.msvc.usuarios.models.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseErrorResponse handleArguments(MethodArgumentNotValidException exception) {

        var errors = new ArrayList<String>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.add("El campo '" + error.getField() + "' " + error.getDefaultMessage()));

        return new ErrorsResponse(HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value(), errors);
    }

    @ExceptionHandler(EmailFoundException.class)
    public BaseErrorResponse handleEmailFound(EmailFoundException exception) {

        return new ErrorResponse(HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }
}
