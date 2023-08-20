package com.example.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String mesage){
        super(mesage);
    }
}
