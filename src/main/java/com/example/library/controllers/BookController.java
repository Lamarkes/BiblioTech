package com.example.library.controllers;
import com.example.library.dto.BookDTO;
import com.example.library.dto.RequestBookDTO;
import com.example.library.dto.RequestUpdateBookDTO;
import com.example.library.entities.Book;
import com.example.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

    @PostMapping(value = "/new")
    public ResponseEntity<Book> createBook(@Validated @RequestBody RequestBookDTO request){
        Book book = new Book(request);
        bookService.createBook(book);
        return ResponseEntity.ok().build();
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody RequestUpdateBookDTO request){
        BookDTO bookDTO = bookService.updateBook(id,request);
        return ResponseEntity.ok().body(bookDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BookDTO> findById(@PathVariable Long id){
        BookDTO result = bookService.findBookById(id);
        return ResponseEntity.ok(result);

    }

    @GetMapping(value = "/books-by-author")
    public ResponseEntity<List<BookDTO>> findByAuthor(@RequestParam String author){
        List<BookDTO> books = bookService.findBooksByAuthor(author);
        return ResponseEntity.ok().body(books);
    }

    @GetMapping(value = "/books-by-author-paginated")
    public ResponseEntity<List<BookDTO>> findByAuthor(@RequestParam String author, Pageable pageable){
        List<BookDTO> books = bookService.findBooksByAuthor(author,pageable);
        return ResponseEntity.ok().body(books);
    }

    @GetMapping(value = "/books-by-title")
    public ResponseEntity<List<BookDTO>> findByTitle(@RequestParam String title){
        List<BookDTO> books = bookService.findBooksByTitle(title);
        return ResponseEntity.ok().body(books);
    }



    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> SoftDeleteBook(@PathVariable Long id){
        bookService.SoftDeleteBook(id);
        return ResponseEntity.ok("Livro excluido com sucesso");
    }
}

