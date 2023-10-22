package com.example.library.entities;
import com.example.library.dtos.BookDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;


// classe principal para representar o livro na base de dados
@Entity // anotaçao para informar para a JPA que essa classe é uma entidade do banco de dados
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "tb_book") // anotaçao para nomear a classe no banco de dados
public class Book implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // estrategia de geração autimantica de Id, de forma incremental
    private Long id;

    @NotBlank
    @Length(min = 2, max = 100)
    private String title;

    @Column(name = "book_year") // renomeando a coluna para outro nome
    private Integer year;

    private String author;

    @Column(name ="book_value")// renomeando a coluna para outro nome

    private Double value;
    @NotNull
    private Integer numPages;
    @NotBlank
    @Length(min = 2, max = 100)
    private String genre;

    private Double rating;

    // configuraçao para definir esta coluna boleana para sempre ser true e renomeando com outro nome
    @Column(columnDefinition = "boolean default true", name = "book_active")
    private Boolean active;

    private String PublishingCompany;

    // definindo esta coluna com o tipo TEXT, para receber mais caracteres
    @Column(columnDefinition = "TEXT")
    private String description;


    public Book(Long id, String title, Integer year, String author, Double value, Integer numPages, String genre, Double rating, String publishingCompany, String description) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.author = author;
        this.value = value;
        this.numPages = numPages;
        this.genre = genre;
        this.rating = rating;
        this.PublishingCompany = publishingCompany;
        this.description = description;
    }

    // metodo para converter de BookDTO para BOOK - normalmente nao sera utilizada
    public Book (BookDTO bookDTO){
        BeanUtils.copyProperties(bookDTO,this);
        this.setActive(true);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
