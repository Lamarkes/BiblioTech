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

import java.util.Optional;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {
    // camada de testes

    @Mock
    BookRepository repository;

    @InjectMocks
    BookService service = new BookService();

    MockBook mockBook;

    @Before
    public void setUp() {
        mockBook = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Saved books")
    public void savedBooksCase1(){
        var bookExpected = mockBook.mockEntity(1);

        var request = mockBook.mockVO(1);

        BookResponseDTO response = new BookResponseDTO(bookExpected);

        when(repository.save(any(Book.class))).thenReturn(bookExpected);

        var bookSaved = service.createBook(request);
        assertEquals(bookSaved.getTitle(), response.getTitle());
        assertEquals(bookSaved.getNumPages(), response.getNumPages());
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

        var list = mockBook.mockEntityList();

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

        var book = mockBook.mockEntity(1);
        when(repository.findById(1L)).thenReturn(Optional.of(book));

        BookResponseDTO result = service.findBookById(1L);

        assertNotNull(result);
        assertEquals("Titulo1", result.getTitle());
        assertEquals("Autor1", result.getAuthor());
        assertEquals(2014, result.getYear());
        assertEquals(20.0, result.getValue());
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
        Book book = mockBook.mockEntity(1);
        var persisted = book;
        persisted.setId(1L);

        BookRequestDTO request = mockBook.mockVO(1);

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
        Book book = mockBook.mockEntity(1);
        when(repository.findById(1L)).thenReturn(Optional.of(book));
        service.disableBook(1L);

        assertFalse(book.getActive());

        verify(repository).findById(1L);
    }
}