package com.mazid.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptions {

    // This method handles all exceptions thrown in the application
    // It returns a custom error response with a BAD_REQUEST status
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> otherExceptionHandler(Exception ex, WebRequest req){
        // Creating an error details object with the exception message, description, and current timestamp
        ErrorDetails error = new ErrorDetails(ex.getMessage(), req.getDescription(false), LocalDateTime.now());

        // Returning the error details inside a ResponseEntity with a BAD_REQUEST status code
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
