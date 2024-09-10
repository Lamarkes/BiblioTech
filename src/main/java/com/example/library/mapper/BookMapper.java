package com.example.library.mapper;

import com.example.library.dto.BookRequestDTO;
import com.example.library.dto.BookUpdateDTO;
import com.example.library.entities.Book;

public class BookMapper {

    private BookMapper(){}


    public static Book updateDtoToEntity(BookUpdateDTO request, Book book){

        book.setTitle(request.getTitle());
        book.setYear(request.getYear());
        book.setValue(request.getValue());
        book.setNumPages(request.getNumPages());
        book.setPublishingCompany(request.getPublishingCompany());
        return book;
    }


    public static Book requestDtoToEntity(BookRequestDTO requestDTO){

        Book book = new Book();

        book.setTitle(requestDTO.title());
        book.setYear(requestDTO.year());
        book.setAuthor(requestDTO.author());
        book.setValue(requestDTO.value());
        book.setNumPages(requestDTO.numPages());
        book.setGenre(requestDTO.genre());
        book.setRating(requestDTO.rating());
        book.setPublishingCompany(requestDTO.publishingCompany());
        book.setDescription(requestDTO.description());


        return book;

    }
}
