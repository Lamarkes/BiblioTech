package com.example.library.repositories;

import com.example.library.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    Optional<Book> findById(Long id);

    List<Book> findByAuthor(String author);

    List<Book> findAllByActiveTrue();

    Page<Book> findByAuthor(String author, Pageable pageable);
}
