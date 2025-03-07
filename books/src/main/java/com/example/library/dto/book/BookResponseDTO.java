package com.example.library.dto;


import com.example.library.entities.Book;
import com.example.library.entities.Publisher;
import com.example.library.enums.GenreBook;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Getter // anotação para substituir os metodos GET
@Setter // anotação para substituir os metodos SET
public class BookResponseDTO extends RepresentationModel<BookResponseDTO> {

    private String title;
    private LocalDate year;
    private String author;
    private BigDecimal value;
    private Integer numPages;
    private GenreBook genre;
    private String description;
    private String publisherName;

    public BookResponseDTO(){}

    public BookResponseDTO(Book book) {
        BeanUtils.copyProperties(book,this);
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        BookResponseDTO that = (BookResponseDTO) object;
        return Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title);
    }
}
