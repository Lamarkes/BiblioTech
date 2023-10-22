package com.example.library.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// camada de exception do sistema, para tratamento de erros de resposta
@RestControllerAdvice // anotaçao do spring responsavel por definir a classe como controle de exceçoes no sistema
public class RequestException {

    // Esta é uma anotação que marca um método como responsável por lidar com exceções do tipo EntityNotFoundException
    // EntityNotFoundException: é uma exceção personalizada que pode ser lançada em algum lugar do código quando
    // uma entidade (por exemplo, um registro de banco de dados) não é encontrada.
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> threat404(){ // este metodo que sera chamado quando ocorrer a exception direta do EntityNotFoundException
        //retorna um objeto ResponseEntity, que é usado para definir a resposta HTTP que será enviada ao cliente.
        return ResponseEntity.badRequest().body("Dado nao encontrado");
    }
}
