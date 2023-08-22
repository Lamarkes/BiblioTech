package com.example.library.services;

import com.example.library.dto.RequestBookDTO;
import com.example.library.dto.RequestUpdateBookDTO;
import com.example.library.exceptions.ExceptionDTO;
import com.example.library.repositories.BookRepository;
import com.example.library.dto.BookDTO;
import com.example.library.entities.Book;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    private ModelMapper modelMapper;

    public BookService(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }


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
            throw new EntityNotFoundException();
        }
    }

    @Transactional
    public List<BookDTO> findBooksByAuthor(String author){
        List<Book> books = bookRepository.findByAuthorAndActiveTrue(author);
        return books.stream().map(BookDTO::new).toList();
    }

    @Transactional
    public List<BookDTO> findBooksByAuthor(String author, Pageable pageable){
        Page<Book> books = bookRepository.findByAuthorAndActiveTrue(author,pageable);
        return books.stream().map(BookDTO::new).toList();
    }

    @Transactional
    public BookDTO updateBook(Long id, RequestUpdateBookDTO request){
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()){
            Book book = optionalBook.get();
            modelMapper.map(request,book);
            bookRepository.save(book);
            return new BookDTO(book);
        }else {
            throw new EntityNotFoundException();
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
            throw new EntityNotFoundException();
        }
    }
}
