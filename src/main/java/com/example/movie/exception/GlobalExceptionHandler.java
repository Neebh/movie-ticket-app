package com.example.movie.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
class GlobalExceptionHandler {
   

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ExceptionMessage> NotFoundException(NotFoundException ex, WebRequest request) {
        ExceptionMessage error = new ExceptionMessage("Not found", ex.getMessage());
        return new ResponseEntity<ExceptionMessage>(error, HttpStatus.BAD_REQUEST);
    }
}
