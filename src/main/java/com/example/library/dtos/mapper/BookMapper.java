package com.example.library.dtos.mapper;

import com.example.library.dtos.BookDTO;
import com.example.library.entities.Book;

public class BookMapper {

    private BookMapper(){}

    public static BookDTO toDTO(Book book) {
        if(book == null) {
            return null;
        }
        var bookDTO = new BookDTO();
        bookDTO.setTitle(book.getTitle());
        bookDTO.setYear(book.getYear());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setValue(book.getValue());
        bookDTO.setNumPages(book.getNumPages());
        bookDTO.setGenre(book.getGenre());
        bookDTO.setRating(book.getRating());
        bookDTO.setPublishingCompany(book.getPublishingCompany());
        bookDTO.setDescription(book.getDescription());
        return bookDTO;
    }

    public static Book toEntity(BookDTO bookDTO){
        if(bookDTO == null) {
            return null;
        }
        var book = new Book();
        if(bookDTO.getId() != null) {
            book.setId(bookDTO.getId());
        }
        book.setTitle(bookDTO.getTitle());
        book.setYear(bookDTO.getYear());
        book.setAuthor(bookDTO.getAuthor());
        book.setValue(bookDTO.getValue());
        book.setNumPages(bookDTO.getNumPages());
        book.setGenre(bookDTO.getGenre());
        book.setRating(bookDTO.getRating());
        book.setPublishingCompany(bookDTO.getPublishingCompany());
        book.setDescription(book.getDescription());
        return book;
    }
}
