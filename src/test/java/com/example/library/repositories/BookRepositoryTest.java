package com.example.library.repositories;

import com.example.library.dto.BookRequestDTO;
import com.example.library.entities.Book;
import com.example.library.unittests.mapper.MockBook;
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

    MockBook mock = new MockBook();

    @Test
    @DisplayName("Get Book Successfully from DB")
    void findByIdCase1() {
        Book book = mock.mockEntity(1);

        Optional<Book> result = this.repository.findById(book.getId());

        assertThat(result).isPresent();
    }

    @Test
    @DisplayName("Not Get Book Successfully")
    void findByIdCase2() {
        Long id = 8L;

        Optional<Book> result = this.repository.findById(id);

        assertThat(result).isEmpty();
    }
}