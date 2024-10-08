package com.example.library.unittests.mapper;
import com.example.library.dto.BookRequestDTO;
import com.example.library.entities.Book;
import com.example.library.entities.Publisher;
import com.example.library.enums.GenreBook;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
public class MockBook {

    public Book mockEntity(){
        return mockEntity(0);
    }

    public BookRequestDTO mockVO(){
        return mockVO(0);
    }

    public List<Book> mockEntityList(){
        List<Book> books = new ArrayList<>();
        for (int i=0;i< 14; i++){
            books.add(mockEntity(i));
        }
        return books;
    }
    public Book mockEntity(int number){
        Book book = new Book();
        book.setId(1L);
        book.setAuthor("Autor"+number);
        book.setDescription("Descricao"+number);
        book.setNumPages(200);
        book.setGenre(GenreBook.ACTION);
        book.setRating(4.2);
        book.setYear(LocalDate.of(2014, Month.APRIL,10));
        book.setValue(new BigDecimal("20"));
        book.setPublisher(
                new Publisher(1L,"editora"+number,"descricao"+1,null,null)
        );
        book.setActive(true);
        book.setTitle("Titulo"+number);
        return book;
    }
    public BookRequestDTO mockVO(int number){
        return new BookRequestDTO(
                "Autor"+number,
                LocalDate.of(2014, Month.APRIL,10),
                "Titulo"+number,
                new BigDecimal("20"),
                200,
                GenreBook.ACTION,
                5.0,
                new Publisher(1L,"editora"+number,"descricao"+1,null,null),
                "Descricao"+number
        );
    }
}