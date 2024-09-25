package com.example.library.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{


    public ResourceNotFoundException(){
        super("No books found!");
    }


    public ResourceNotFoundException(String ex){
        super(ex);
    }
}
