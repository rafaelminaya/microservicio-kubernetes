package org.rminaya.springcloud.msvc.usuarios.controllers.error_handler;

import org.rminaya.springcloud.msvc.usuarios.models.exceptions.BaseErrorResponse;
import org.rminaya.springcloud.msvc.usuarios.models.exceptions.ErrorResponse;
import org.rminaya.springcloud.msvc.usuarios.models.exceptions.ErrorsResponse;
import org.rminaya.springcloud.msvc.usuarios.models.exceptions.IdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;

@RestControllerAdvice
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundController {

    @ExceptionHandler(IdNotFoundException.class)
    public BaseErrorResponse handleIdNotFound(IdNotFoundException exception) {

        return new ErrorResponse(HttpStatus.BAD_REQUEST.name(), HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }
}
