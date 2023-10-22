package com.example.library.services;

import com.example.library.dtos.BookDTO;
import com.example.library.entities.Book;
import com.example.library.repositories.BookRepository;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class BookServiceTest {


    @Mock
    private BookRepository repository;

    @InjectMocks
    private BookService service;
    Book book1;
    Book book2;
    Book book3;
    List<Book> books;

    @Before
    public void setUp() {

        books = new ArrayList<>(List.of(
                book1 = new Book(1L, "Titulo 1", 2023, "Autor 1", 10.0, 200, "Genero 1", 5.0, "Publi 1", "descriçao 1"),
                book2 = new Book(2L, "Titulo 2", 2023, "Autor 2", 10.0, 200, "Genero 2", 5.0, "Publi 2", "descriçao 2"),
                book3 = new Book(3L, "Titulo 3", 2023, "Autor 3", 10.0, 200, "Genero 3", 5.0,"Publi 3", "descriçao 3")
        ));
    }

    @Test
    @DisplayName("Saved books")
    public void savedBooksCase1(){
        final var bookExpected = books.get(1);
        final var bookDTO = new BookDTO(bookExpected);

        when(repository.save(bookExpected)).thenReturn(bookExpected);

        final var bookSaved = service.createBook(bookDTO);
        assertEquals(bookExpected, bookSaved);
    }


    @Test
    @DisplayName("Find books")
    public void findAllBooksCase1() {
        when(repository.findAllByActiveTrue()).thenReturn(Collections.singletonList(book1));

        final var bookList = service.findAllBooks();
        assertEquals(Collections.singletonList(book1), bookList);
        verify(repository).findAllByActiveTrue();
        verifyNoMoreInteractions(repository);
    }
}
