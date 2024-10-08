package com.example.library.repositories;

import com.example.library.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher,Long> {

    Optional<Publisher> findById(Long Long);
}
