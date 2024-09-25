package com.example.library.unittests.mapper;

import com.example.library.dto.BookRequestDTO;
import com.example.library.entities.Book;

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
        book.setPublishingCompany("Companhia"+number);
        book.setNumPages(200);
        book.setGenre("Genero"+number);
        book.setRating(4.2);
        book.setYear(2014);
        book.setValue(20D);
        book.setActive(true);
        book.setTitle("Titulo"+number);

        return book;
    }

    public BookRequestDTO mockVO(int number){
        return new BookRequestDTO(
                "Autor"+number,
                2014,
                "Titulo"+number,
                20D,
                200,
                "Genero"+number,
                5.0,
                "Companhia"+number,
                "Descricao"+number
        );
    }
}
