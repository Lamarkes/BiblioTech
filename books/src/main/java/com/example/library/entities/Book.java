package com.example.library.entities;
import com.example.library.enums.GenreBook;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;


// classe principal para representar o livro na base de dados
@Entity // anotaçao para informar para a JPA que essa classe é uma entidade do banco de dados
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Table(name = "tb_book") // anotaçao para nomear a classe no banco de dados
public class Book implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // estrategia de geração autimantica de Id, de forma incremental
    private Long id;

    @Column(nullable = false)
    @Length(min = 2, max = 100)
    private String title;

    @Column(name = "book_year") // renomeando a coluna para outro nome
    private LocalDate year;

    @Column(name = "author_name", nullable = false) // relacionamento com a tableba autor
    private String author;

    @Column(name ="book_value")// renomeando a coluna para outro nome
    private BigDecimal value;

    @Column(name ="num_pages")
    private Integer numPages;

    @Enumerated(value = EnumType.STRING)
    private GenreBook genre;

    private Double rating;

    // configuraçao para definir esta coluna boleana para sempre ser true e renomeando com outro nome
    @Column(name = "book_active")
    private Boolean active = true;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "editora_id", nullable = false)
    @JsonBackReference
    private Publisher publisher;

    // definindo esta coluna com o tipo TEXT, para receber mais caracteres
    @Column(columnDefinition = "TEXT")
    private String description;

    // metodo para converter de BookDTO para BOOK - normalmente nao sera utilizada
    public Book(){}

}
