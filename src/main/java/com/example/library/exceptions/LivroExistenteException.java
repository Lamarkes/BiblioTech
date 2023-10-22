package com.example.library.exceptions;

import jakarta.persistence.EntityExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LivroExistenteException {

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<String> threat404(){
        return ResponseEntity.badRequest().body("Livro já está registrado!");
    }

}
