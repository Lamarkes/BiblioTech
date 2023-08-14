package com.example.library.services;

import com.example.library.BookRepository;
import com.example.library.dto.BookDTO;
import com.example.library.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    @Transactional(readOnly = true)
    public List<BookDTO> findAll(){
        List<Book> result = bookRepository.findAll();
        return result.stream().map(BookDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public BookDTO findById(Long id){
        Book book = bookRepository.findById(id).get();
        return new BookDTO(book);
    }
}
