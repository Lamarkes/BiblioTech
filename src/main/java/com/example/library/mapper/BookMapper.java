package com.example.library.mapper;

import com.example.library.dto.BookUpdateDTO;
import com.example.library.entities.Book;

public class BookMapper {


    public static Book dtoToEntity(BookUpdateDTO request, Book book){

        book.setTitle(request.getTitle());
        book.setYear(request.getYear());
        book.setValue(request.getValue());
        book.setNumPages(request.getNumPages());
        book.setPublishingCompany(request.getPublishingCompany());

        return book;

    }
}
