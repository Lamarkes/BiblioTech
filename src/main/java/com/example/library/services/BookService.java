package com.example.library.services;
import com.example.library.exceptions.LivroExistenteException;
import com.example.library.repositories.BookRepository;
import com.example.library.dtos.BookDTO;
import com.example.library.entities.Book;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;


// camada de Service, nela tera a logica de servico do sistema
@Service // anotaçao para indicar que esta classe e responsavel pelo Service
public class BookService {

    // injeçao de dependencia do bookRepository
    //seguindo o modelo de desenvolvimento em que o repository deve ser utilizado na camada de Service

    @Autowired
    private  BookRepository bookRepository;
    private ModelMapper modelMapper; // utilizaçao do ModelMapper que realiza mapeamento de um tipo para outro


    // construtor da classe bookServicce - injeçao de dependencia do modelMapper
    // assim que for utilizada, ele injeta a dependencia do modelMapper
    // serve justamente para fazer o mapeamento de um tipo para outro, evitando muitas linhas de codigo
    public BookService(ModelMapper modelMapper, BookRepository bookRepository) {
        this.modelMapper = modelMapper;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public List<Book> findAllBooks(){
        return bookRepository.findAllByActiveTrue();

    }

    @Transactional(readOnly = true)
    public BookDTO findBookById(Long id){
        Optional<Book> optionalbook = bookRepository.findById(id);
        if (optionalbook.isPresent()) {
            Book book = optionalbook.get();
            return new BookDTO(book);
        }else {
            throw new EntityNotFoundException();
        }
    }

    @Transactional
    public List<BookDTO> findBooksByAuthor(String author){
        List<Book> books = bookRepository.findByAuthorAndActiveTrue(author);
        if (books.isEmpty()){
            throw new RuntimeException("Nenhum registro para este autor!");
        }else {
            return books.stream().map(BookDTO::new).toList();
        }

    }

    @Transactional
    public List<BookDTO> findBooksByAuthor(String author, Pageable pageable){
        Page<Book> books = bookRepository.findByAuthorAndActiveTrue(author,pageable);
        return books.stream().map(BookDTO::new).toList();
    }

    @Transactional
    public BookDTO findBooksByTitle(String title){
        Optional<Book> optionalBook = bookRepository.findByTitleAndActiveTrue(title);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            return new BookDTO(book);
        }else {
            throw new EntityNotFoundException();
        }
    }

    @Transactional
    public BookDTO updateBook(Long id, BookDTO bookDTO){
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()){
            Book book = optionalBook.get();
            modelMapper.map(bookDTO,book);
            bookRepository.save(book);
            return new BookDTO(book);
        }else {
            throw new EntityNotFoundException();
        }
    }
    @Transactional
    public Book createBook(BookDTO bookDTO){
        try {
            Book book = new Book(bookDTO);
            return bookRepository.save(book);
        }catch (DataAccessException e ){
           throw new RuntimeException("Erro ao criar livro!", e);
        }
    }

    @Transactional
    public void DisableBook(Long id){
        Optional<Book> OptionalBook = bookRepository.findById(id);

        if (OptionalBook.isPresent()){
            Book book = OptionalBook.get();
            book.setActive(false);
            bookRepository.save(book);
        }else {
            throw new EntityNotFoundException();
        }
    }
}
