package com.example.library.dto;


// Classe seguingo o padrao DTO
// ao inves de utilizar a classe Book, passa a utilzar uma classe DTO para manipular dados de maneira eficiente


public record BookRequestDTO(
        String title,
        Integer year,
        String author,
        Double value,
        Integer numPages,
        String genre,
        Double rating,
        String publishingCompany,
        String description) {

}
