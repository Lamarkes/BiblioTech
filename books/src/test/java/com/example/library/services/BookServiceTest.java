package com.example.library.services;

import com.example.library.dto.BookRequestDTO;
import com.example.library.dto.BookResponseDTO;
import com.example.library.dto.BookUpdateDTO;
import com.example.library.entities.Book;
import com.example.library.exceptions.RequiredObjectIsNullException;
import com.example.library.exceptions.ResourceNotFoundException;
import com.example.library.repositories.BookRepository;
import com.example.library.unittests.mapper.MockBook;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class BookServiceTest {
    // camada de testes

    @Mock
    private BookRepository repository;

    @InjectMocks
    private BookService service;

    MockBook input;

    @Before
    public void setUp() {
        input = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Saved books")
    public void savedBooksCase1(){
        var bookExpected = input.mockEntity(1);
        var request = input.mockVO(1);

        BookResponseDTO response = new BookResponseDTO(bookExpected);

        when(repository.save(any(Book.class))).thenReturn(bookExpected);
        var bookSaved = service.createBook(request);
        assertEquals(bookSaved.getTitle(), response.getTitle());
        assertEquals(bookSaved.getNumPages(), response.getNumPages());
        assertEquals(bookSaved.getAuthor(), response.getAuthor());
    }
    @Test
    @DisplayName("Books error")
    public void savedBooksCase2(){
        Exception exception = assertThrows(RequiredObjectIsNullException.class, ()
                -> service.createBook(null));
        String expectMessage = "It is not allowed to persist a null object";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectMessage));
    }


    @Test
    @DisplayName("Find all books")
    public void findAllBooksCase1() {

        var list = input.mockEntityList();
        when(repository.findAllByActiveTrue()).thenReturn(list);
        var bookList = service.findAllBooks();
        assertNotNull(list);
        assertEquals(14, bookList.size());
        verify(repository).findAllByActiveTrue();
        verifyNoMoreInteractions(repository);
    }
    @Test
    @DisplayName("Test find book by Id")
    public void findBookByIdCase1(){
        var book = input.mockEntity(1);
        when(repository.findById(1L)).thenReturn(Optional.of(book));
        BookResponseDTO result = service.findBookById(1L);
        assertNotNull(result);
        assertEquals("Titulo1", result.getTitle());
        assertEquals("Autor1", result.getAuthor());
        assertEquals(book.getYear(), result.getYear());
        assertEquals(new BigDecimal(20), result.getValue());
        (repository).findById(1L);
    }
    @Test
    @DisplayName("Test Exception to id doesn't exists")
    public void findBookByIdCase2(){
        Exception exception = assertThrows(ResourceNotFoundException.class, () ->{
            service.findBookById(4L);
        });
        String expectedMessage = "No books found for this id!";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
    @Test
    @DisplayName("Test Update book")
    public void updateBookCase1(){
        Book book = input.mockEntity(1);
        var persisted = book;
        persisted.setId(1L);
        BookRequestDTO request = input.mockVO(1);
        when(repository.findById(1L)).thenReturn(Optional.of(book));
        when(repository.save(book)).thenReturn(persisted);
        BookUpdateDTO updatedBook = new BookUpdateDTO(book);
        var result = service.updateBook(1L, updatedBook);
        assertNotNull(request);
        assertNotNull(result.getAuthor());
        assertEquals("Titulo1", result.getTitle());
    }
    @Test
    @DisplayName("Test for delete book")
    public void disableBook(){
        Book book = input.mockEntity(1);
        when(repository.findById(1L)).thenReturn(Optional.of(book));
        service.disableBook(1L);
        assertFalse(book.getActive());
        verify(repository).findById(1L);
    }
}
