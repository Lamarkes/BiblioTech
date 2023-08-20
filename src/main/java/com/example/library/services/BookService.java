package com.example.library.services;

import com.example.exceptions.ResourceNotFoundException;
import com.example.library.BookRepository;
import com.example.library.dto.BookDTO;
import com.example.library.entities.Book;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    @Transactional(readOnly = true)
    public List<BookDTO> findAllBooks(){
        List<Book> result = bookRepository.findAllByActiveTrue();
        return result.stream().map(BookDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public BookDTO findBookById(Long id){
        Optional<Book> optionalbook = bookRepository.findById(id);
        if (optionalbook.isPresent()) {
            Book book = optionalbook.get();
            return new BookDTO(book);
        }else {
            throw new ResourceNotFoundException("Id nao encontrado!");
        }
    }

    @Transactional
    public void createBook(Book book){
        try {
            bookRepository.save(book);
        }catch (Exception e){
            throw new RuntimeException("Erro ao criar livro !");
        }
    }

    @Transactional
    public void deleteBook(Long id){
        Optional<Book> OptionalBook = bookRepository.findById(id);

        if (OptionalBook.isPresent()){
            Book book = OptionalBook.get();
            book.setActive(false);
            bookRepository.save(book);
        }else {
            throw new EntityNotFoundException("Livro nao encontrado!");
        }
    }
}
