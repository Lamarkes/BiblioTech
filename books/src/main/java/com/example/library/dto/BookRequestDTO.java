package com.example.library.dto;


// Classe seguingo o padrao DTO
// ao inves de utilizar a classe Book, passa a utilzar uma classe DTO para manipular dados de maneira eficiente


import com.example.library.entities.Publisher;
import com.example.library.enums.GenreBook;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public record BookRequestDTO(
        String title,
        LocalDate year,
        String author,
        BigDecimal value,
        Integer numPages,
        GenreBook genre,
        Double rating,
        Publisher publisher,
        String description) {
}
