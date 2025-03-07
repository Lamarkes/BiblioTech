package com.example.library.dto.book;


// Classe seguingo o padrao DTO
// ao inves de utilizar a classe Book, passa a utilzar uma classe DTO para manipular dados de maneira eficiente


import com.example.library.entities.Publisher;
import com.example.library.enums.Format;
import com.example.library.enums.GenreBook;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDTO{

    private String title;
    private LocalDate year;
    private String language;
    private Format format;
    private String author;
    private BigDecimal value;
    private String license;
    private Integer numPages;
    private GenreBook genre;
    private Double rating;
    private Publisher publisher;
    private String description;
}
