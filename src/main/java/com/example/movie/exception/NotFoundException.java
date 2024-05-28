package com.example.movie.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String messaString){

        super(messaString);
    }
    
}
