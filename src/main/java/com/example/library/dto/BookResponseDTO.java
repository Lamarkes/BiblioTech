package com.example.library.dto;


import com.example.library.entities.Book;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter // anotação para substituir os metodos GET
@Setter // anotação para substituir os metodos SET
public class BookResponseDTO {

    private String title;
    private Integer year;
    private String author;
    private Double value;
    private Integer numPages;
    private String genre;
    private String publishingCompany;
    private String description;

    public BookResponseDTO(){}

    public BookResponseDTO(Book book) {
        BeanUtils.copyProperties(book,this);
    }
}
