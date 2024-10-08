package com.example.library.dto;


import com.example.library.entities.Book;
import com.example.library.entities.Publisher;
import com.example.library.enums.GenreBook;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Getter // anotação para substituir os metodos GET
@Setter // anotação para substituir os metodos SET
public class BookResponseDTO {

    private String title;
    private LocalDate year;
    private String author;
    private BigDecimal value;
    private Integer numPages;
    private GenreBook genre;
    private String description;
    private Long publisherId;

    public BookResponseDTO(){}

    public BookResponseDTO(Book book) {
        BeanUtils.copyProperties(book,this);
    }
}
