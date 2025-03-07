package com.example.library.services;
import com.example.library.controllers.BookController;
import com.example.library.dto.book.BookResponseDTO;
import com.example.library.dto.book.BookUpdateDTO;

import com.example.library.exceptions.RequiredObjectIsNullException;
import com.example.library.exceptions.ResourceNotFoundException;
import com.example.library.mapper.BookMapper;
import com.example.library.repositories.BookRepository;
import com.example.library.dto.book.BookRequestDTO;
import com.example.library.entities.Book;
import com.example.library.repositories.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

// camada de Service, nela tera a logica de servico do sistema
@Service // anotaçao para indicar que esta classe e responsavel pelo Service
@RequiredArgsConstructor
public class BookService {

    // injeçao de dependencia do bookRepository
    //seguindo o modelo de desenvolvimento em que o repository deve ser utilizado na camada de Service

    final BookRepository bookRepository;

    final PublisherRepository publisherRepository;

    // utilizaçao do ModelMapper que realiza mapeamento de um tipo para outro
    public List<BookResponseDTO> findAllBooks() {

        var books = BookMapper.parseListObjects(bookRepository.findAllByActiveTrue(), BookResponseDTO.class);

        books.forEach(
                book ->book.add(linkTo(methodOn(BookController.class).findByTitle(book.getTitle())).withSelfRel())
        );

        return books;
    }

    @Transactional(readOnly = true)
    public BookResponseDTO findBookById(Long id) {

        var book = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No books found for this id!")
        );

        var response = BookMapper.parseObject(book, BookResponseDTO.class);
        response.add(linkTo(methodOn(BookController.class).findByTitle(response.getTitle())).withSelfRel());

        return response;
    }


    @Transactional
    public List<BookResponseDTO> findBooksByAuthor(String author) {
        var books = bookRepository.findByAuthorAndActiveTrue(author);
        if (books.isEmpty()) throw new ResourceNotFoundException("No books found for this author!");
        var response = BookMapper.parseListObjects(books, BookResponseDTO.class);

        response.forEach(
                book -> book.add(linkTo(methodOn(BookController.class)
                        .findByTitle(book.getTitle())).withSelfRel())
        );
        return response;
    }

    @Transactional
    public List<BookResponseDTO> findBooksByAuthor(String author, Pageable pageable) {
        Page<Book> books = bookRepository.findByAuthorAndActiveTrue(author, pageable);

        return books.stream().map(BookResponseDTO::new).toList();
    }

    @Transactional
    public BookResponseDTO findBooksByTitle(String title) {
        var book = bookRepository.findByTitleAndActiveTrue(title).orElseThrow(
                () -> new ResourceNotFoundException("No books found with this title!"));
        var response = BookMapper.parseObject(book, BookResponseDTO.class);
        response.add(linkTo(methodOn(BookController.class).findByTitle(response.getTitle())).withSelfRel());
        return response;
    }

    @Transactional
    public BookResponseDTO updateBook(Long id, BookUpdateDTO bookUpdateDTO) {

        var book = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No books found for this id!")
        );

        Book updatedBook = BookMapper.updateBookDtoToEntity(bookUpdateDTO, book);
        updatedBook.setUpdatedAt(LocalDate.now());

        var response = BookMapper.parseObject(bookRepository.save(updatedBook), BookResponseDTO.class);

        response.add(linkTo(methodOn(BookController.class).findByTitle(response.getTitle())).withSelfRel());

        return response;
    }


    public BookResponseDTO createBook(BookRequestDTO bookRequestDTO){

        if (bookRequestDTO == null) throw
                new RequiredObjectIsNullException("It is not allowed to persist a null object");

        var publisher = publisherRepository.findByName(bookRequestDTO.getPublisher().getName()).orElseThrow(
                () -> new ResourceNotFoundException("No publishers found for this name!")
        );

        var book = BookMapper.parseObject(bookRequestDTO, Book.class);
        book.setPublisher(publisher);


        var response = BookMapper.parseObject(bookRepository.save(book), BookResponseDTO.class);
        response.add(linkTo(methodOn(BookController.class).findByTitle(response.getTitle())).withSelfRel());

        return response;

    }

    public void disableBook(Long id){
        var book = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No books found for this id!")
        );

            book.setActive(false);
            bookRepository.save(book);
        }
    }

