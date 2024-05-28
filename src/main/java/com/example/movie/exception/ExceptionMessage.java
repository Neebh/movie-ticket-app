package com.example.movie.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionMessage {

    String message;
    String details;

    public ExceptionMessage(String message, String details) {
        this.message = message;
        this.details = details;
    }
    
}
