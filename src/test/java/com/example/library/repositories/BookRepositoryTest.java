package com.example.library.repositories;

import com.example.library.dtos.BookDTO;
import com.example.library.entities.Book;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class BookRepositoryTest {

    @Autowired
    BookRepository repository;

    @Test
    @DisplayName("Get Book Successfully from DB")
    void findByIdCase1() {
        BookDTO bookDTO = new BookDTO(1L,"Book 1",2012,"Author 1",20.0,30,"Genre 1",5.0,true,"Publishing 1","description 1");
        this.createBook(bookDTO);

        Optional<Book> result = this.repository.findById(bookDTO.getId());

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Not Get Book Successfully")
    void findByIdCase2() {
        Long id = 8L;

        Optional<Book> result = this.repository.findById(id);

        assertThat(result.isEmpty()).isTrue();
    }

    private void createBook(BookDTO bookDTO){
        Book newBook = new Book(bookDTO);
        this.repository.save(newBook);
    }
}