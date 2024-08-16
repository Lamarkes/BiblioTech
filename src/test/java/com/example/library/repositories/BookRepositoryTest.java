package com.example.library.repositories;

import com.example.library.dto.BookRequestDTO;
import com.example.library.entities.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
class BookRepositoryTest {

    @Autowired
    BookRepository repository;

//    @Test
//    @DisplayName("Get Book Successfully from DB")
//    void findByIdCase1() {
//        BookRequestDTO bookRequestDTO = new BookRequestDTO(1L,"Book 1",2012,"Author 1",20.0,30,"Genre 1",5.0,true,"Publishing 1","description 1");
//        this.createBook(bookRequestDTO);
//
//        Optional<Book> result = this.repository.findById(bookRequestDTO.getId());
//
//        assertThat(result.isPresent()).isTrue();
//    }

    @Test
    @DisplayName("Not Get Book Successfully")
    void findByIdCase2() {
        Long id = 8L;

        Optional<Book> result = this.repository.findById(id);

        assertThat(result.isEmpty()).isTrue();
    }

    private void createBook(BookRequestDTO bookRequestDTO){
        Book newBook = new Book(bookRequestDTO);
        this.repository.save(newBook);
    }
}