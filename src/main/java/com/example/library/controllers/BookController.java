package com.example.library.controllers;

import com.example.library.dto.BookDTO;
import com.example.library.dto.RequestBookDTO;
import com.example.library.entities.Book;
import com.example.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity findAll(){
        List<BookDTO> bookDTO = bookService.findAll();
        return ResponseEntity.ok().body(bookDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        BookDTO result = bookService.findById(id);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping(value = "/new")
    public ResponseEntity createBook(@RequestBody RequestBookDTO request){
        Book book = new Book(request);
        bookService.createBook(book);
        return ResponseEntity.ok().build();
    }

}

