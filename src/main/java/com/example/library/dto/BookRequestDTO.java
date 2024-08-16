package com.example.library.dto;


// Classe seguingo o padrao DTO
// ao inves de utilizar a classe Book, passa a utilzar uma classe DTO para manipular dados de maneira eficiente


import com.example.library.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

@Getter // anotação para substituir os metodos GET
@Setter // anotação para substituir os metodos SET

public class BookRequestDTO {

    // varivaeis da classe Book que podem ser utilizadas, as que nao estao aqui nao sao modificaveis
    private Long id;
    @Length(min = 2, max = 100)
    private String title;
    private Integer year;
    private String author;
    private Double value;
    private Integer numPages;
    @Length(min = 2, max = 100)
    private String genre;
    private Double rating;
    private Boolean active;
    private String PublishingCompany;
    private String description;

    public BookRequestDTO(){}

    // é utilizada para seguir o padrao que diz que o service so deve retornar DTOs
    public BookRequestDTO(Book book){ // construtor que copia todos os valores do bookDTO para o Book
        BeanUtils.copyProperties(book,this);
    }

}
