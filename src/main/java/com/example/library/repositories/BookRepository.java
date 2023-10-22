package com.example.library.repositories;
import com.example.library.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


// camada de Repository do sistema, onde sera feita as consultas e configuraçoes com o banco de dados

@Repository // anotaçao para informar ao spring que esta interface e responsavel pelo repository
// a interface extende do jpaRepository que ja contem muitos metodos para consultas no banco de dados
public interface BookRepository extends JpaRepository<Book,Long> {

    // assinatura de um metodo do tipo Optional que realiza uma busca pelo ID

    Optional<Book> findById(Long id);

    //assinatura de um metodo do tipo list que faz uma busca de livros por autor e por status igual a true
    List<Book> findByAuthorAndActiveTrue(String author);

    // assinatura que busca todos os livros do sistema que estejam com o status igual a true
    List<Book> findAllByActiveTrue();

    //metodo do tipo Optional que faz uma busca de livros por titulo e por status igual a true
    Optional<Book> findByTitleAndActiveTrue(String title);

    //assinatura de um metodo do tipo page que faz uma busca de livros por autor e por status igual a true
    // esta assinatura introduz o modelo de paginanao e numeraçao de paginas, com a quantidade de livros que irao aparecer na pagina
    Page<Book> findByAuthorAndActiveTrue(String author, Pageable pageable);
}
