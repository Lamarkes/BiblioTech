package com.example.library.controllers;
import com.example.library.dto.BookDTO;
import com.example.library.dto.RequestBookDTO;
import com.example.library.entities.Book;
import com.example.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDTO>> findAll(){
        List<BookDTO> book = bookService.findAllBooks();
        return ResponseEntity.ok().body(book);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BookDTO> findById(@PathVariable Long id){
        BookDTO result = bookService.findBookById(id);
        return ResponseEntity.ok(result);

    }

    @PostMapping(value = "/new")
    public ResponseEntity<Book> createBook(@Validated @RequestBody RequestBookDTO request){
        Book book = new Book(request);
        bookService.createBook(book);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.ok("Livro excluido com sucesso");
    }
}

