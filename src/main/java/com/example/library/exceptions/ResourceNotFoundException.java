package com.example.library.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String mesage){
        super(mesage);
    }
}
