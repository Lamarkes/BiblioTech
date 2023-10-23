package com.example.library.dtos;

import com.example.library.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;
@Getter // anotação para substituir os metodos GET
@Setter // anotação para substituir os metodos SET
@AllArgsConstructor
@NoArgsConstructor // anotação para substituir o construtor sem argumentos
public class BookUpdateDTO {

    private String title;
    private Integer year;
    private Double value;
    private Integer numPages;
    private String PublishingCompany;

    public BookUpdateDTO(Book book){ // construtor que copia todos os valores do bookDTO para o Book
        BeanUtils.copyProperties(book,this);
    }
}
