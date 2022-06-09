package com.vinisolon.fullstackcourse.resources.exceptions;

import com.vinisolon.fullstackcourse.services.exceptions.DataIntegrityException;
import com.vinisolon.fullstackcourse.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandartError> objectNotFound(ObjectNotFoundException exception, HttpServletRequest request) {
        StandartError error = new StandartError(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                request.getRequestURI(),
                Instant.now()
        );

        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandartError> dataIntegrityViolation(DataIntegrityException exception, HttpServletRequest request) {
        StandartError error = new StandartError(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                request.getRequestURI(),
                Instant.now()
        );

        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> MethodArgumentNotValid(MethodArgumentNotValidException exception, HttpServletRequest request) {
        ValidationError error = new ValidationError(
                HttpStatus.BAD_REQUEST.value(),
                "Bean validation failed",
                request.getRequestURI(),
                Instant.now()
        );

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(fieldError -> error.setEachError(fieldError.getField(), fieldError.getDefaultMessage()));

        return ResponseEntity.status(error.getStatus()).body(error);
    }

}
