package com.example.library.controllers;
//importaçoes utilizadas para realizaçao do sistema
import com.example.library.dtos.BookDTO;
import com.example.library.dtos.BookUpdateDTO;
import com.example.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// Camada de Controle do sistema

@RestController// Anotação que indica para o Spring que esta classe sera um controlador Rest
@RequestMapping("/books") // anotação que define o metodo de Request passando como valor /books - ira retornar todos os livros como padrao
public class BookController {

    // Injeçao de dependencia diretamente da camada de serviço
    @Autowired
    private BookService bookService;


     BookController(BookService bookService){
        this.bookService = bookService;
    }

    // primeira implementaçao Get do sistema - FindAll
    // esta funçao ira retornar todos os livros que estao atualmente ativos no sistema diretamente do Banco de Dados
    @GetMapping
    public ResponseEntity<List<BookDTO>> findAllBooks(){
        return ResponseEntity.ok(bookService.findAllBooks().stream()
                .map(BookDTO::new).toList()); // quando for realizado o GET, retornara todos os livros como resposta
    }

    //Primeira implementaçao Post do sistema - createBook
    // esta funçao ira adicionar um novo livro no banco de dados a partir das informaçoes que forem passadas

    // anotaçao que indica que sera feito um Post
    @PostMapping(value = "/new") // para ser realizada, deve ser passado o caminho "books/new"
    public ResponseEntity<BookDTO> createBook(@Validated @RequestBody BookDTO bookDTO){ // indica que deve ser inserido um corpo na requisiçao que sera as informaçoes do novo livro
        var book = bookService.createBook(bookDTO); // pega todas as informaçoes passadas como corpo e converte de RequestBookDTO em um Book
        BookDTO createdBookDTO = new BookDTO(book);
        // apos ser convertido para um Book e passado pelas validaçoes, sera salvo no banco de dados com um metodo do bookService
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBookDTO); // por fim sera retornado uma resposta informando que o livro foi adicionado
    }
    //Metodo Put - updateBook
    // esta funcionalidade ira atualziar um livro que for buscado por um Id existente

    // esta anotaçao indica que sera realizado um Put
    @PutMapping(value = "/update/{id}") // para relaizar, deve passar o caminho "books/update/id"
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookUpdateDTO request){ // o livro so podera ser atualizado passando um Id de livro existente e consequentemente os dados que deseja atualziar
        BookDTO bookDTO = bookService.updateBook(id,request); // diretamente do bookService,o livro sera atualizado, passamos o id e as informaçoes que devsejamos atualziar em um BookDTO
        return ResponseEntity.ok().body(bookDTO); // por fim retornara o livro com os campos escolhidos com os dados que foram atualizados
    }

    //funçao Get - findByID
    // esta funçao fara uma bsuca no banco de dados do sistema pelo Id existente

    @GetMapping(value = "/{id}")// para ser utilizada, basta passar "books/numero-do-id"
    // devera receber um Id como variavel de caminho que sera utilizado na bsuca
    public ResponseEntity<BookDTO> findById(@PathVariable Long id){
        //a busca do livro pelo id no banco de dados sera realziada pelo findByBook do bookService e o livro que for encontrado sera salvo na variavel result
        BookDTO result = bookService.findBookById(id);
        // apos encontrar o livro e salva-lo na variavel, sera retornado o livro que foi encontrado com o Id passado
        return ResponseEntity.ok(result);

    }
    //funçao Get - findByAuthor
    // esta funçao fara uma bsuca no banco de dados do sistema pelo nome do autor
    @GetMapping(value = "/books-by-author") // caminho utilizado para realizar a busca
    public ResponseEntity<List<BookDTO>> findByAuthor(@RequestParam String author){ // passando o nome do autor como paramentro
        List<BookDTO> books = bookService.findBooksByAuthor(author); // ira retornar uma lista com todos os livros que foram publicados pelo autor que foi informado
        return ResponseEntity.ok().body(books); // a lista sera retornada como resposta
    }

    //funçao Get - findByAuthor
    // mesma funcao utilizada a cima, mas utilizando metodo de paginaçao e ordenaçao

    @GetMapping(value = "/books-by-author-paginated") // caminho para realizar a busca paginada
    public ResponseEntity<List<BookDTO>> findByAuthor(@RequestParam String author, Pageable pageable){ // deve passar o nome do autor e o numero de paginaçoies.
        List<BookDTO> books = bookService.findBooksByAuthor(author,pageable); // sera passado para uma lista
        return ResponseEntity.ok().body(books); // retornado como resposta
    }

    //funçao Get - findBytitle
    // esta funçao fara uma bsuca no banco de dados do sistema pelo titulo do livro

    @GetMapping(value = "/books-by-title") // caminho para realizar a busca
    public ResponseEntity<BookDTO> findByTitle(@RequestParam String title){ // sera passado o titulo do livro como paramentro
        BookDTO book = bookService.findBooksByTitle(title); // sera realizado a busca do livro e se existir, sera salvo na variavel
        return ResponseEntity.ok().body(book); // retornara o livro encontrado como resposta
    }

    // funcao Delete - softdelete
    // esta funcao ira remover os livros que estejam com a varivavel active = false, uma abordagem para desativar o livro e nao deletar por compmeto
    @DeleteMapping(value = "/disable/{id}") // caminho utilizado para desativar o livro
    public ResponseEntity<String> DisableBook(@PathVariable Long id){ // o livro sera buscado por id
        bookService.DisableBook(id); // se o livro existir, seu active sera passado para false e sera desativado
        return ResponseEntity.ok("Livro desativado com sucesso"); // retorna a mensagem de desativaçao
    }
}

