package com.example.library.services;
import com.example.library.dto.BookResponseDTO;
import com.example.library.dto.BookUpdateDTO;

import com.example.library.entities.Publisher;
import com.example.library.exceptions.RequiredObjectIsNullException;
import com.example.library.exceptions.ResourceNotFoundException;
import com.example.library.mapper.BookMapper;
import com.example.library.mapper.Mapper;
import com.example.library.repositories.BookRepository;
import com.example.library.dto.BookRequestDTO;
import com.example.library.entities.Book;
import com.example.library.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

// camada de Service, nela tera a logica de servico do sistema
@Service // anotaçao para indicar que esta classe e responsavel pelo Service
public class BookService {

    // injeçao de dependencia do bookRepository
    //seguindo o modelo de desenvolvimento em que o repository deve ser utilizado na camada de Service
    @Autowired
    private BookRepository bookRepository;
    
    // utilizaçao do ModelMapper que realiza mapeamento de um tipo para outro
    @Transactional
    public List<BookResponseDTO> findAllBooks() {
        return Mapper
                .parseListObjects(bookRepository.findAllByActiveTrue(), BookResponseDTO.class);
    }

    @Transactional(readOnly = true)
    public BookResponseDTO findBookById(Long id) {

        var book = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No books found for this id!")
        );
        return Mapper.parseObject(book, BookResponseDTO.class);
    }


    @Transactional
    public List<BookResponseDTO> findBooksByAuthor(String author) {
        var books = bookRepository.findByAuthorAndActiveTrue(author);
        if (books.isEmpty()) throw new ResourceNotFoundException("No books found for this author!");

        return Mapper.parseListObjects(books, BookResponseDTO.class);
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

            return Mapper.parseObject(book, BookResponseDTO.class);
    }

    @Transactional
    public BookResponseDTO updateBook(Long id, BookUpdateDTO bookUpdateDTO) {

        var book = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No books found for this id!")
        );

        Book updatedBook = BookMapper.updateDtoToEntity(bookUpdateDTO, book);

        return Mapper.parseObject(bookRepository.save(updatedBook), BookResponseDTO.class);
    }


    public BookResponseDTO createBook(BookRequestDTO bookRequestDTO){

        if (bookRequestDTO == null) throw
                new RequiredObjectIsNullException("It is not allowed to persist a null object");

        var book = BookMapper.requestDtoToEntity(bookRequestDTO);

        return Mapper.parseObject(bookRepository.save(book), BookResponseDTO.class);

    }

    @Transactional
    public void disableBook(Long id){
        var book = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("No books found for this id!")
        );

            book.setActive(false);
            bookRepository.save(book);
        }
    }

