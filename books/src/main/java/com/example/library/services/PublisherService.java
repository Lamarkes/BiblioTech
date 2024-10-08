package com.example.library.services;

import com.example.library.dto.PublisherResponseDTO;
import com.example.library.entities.Publisher;
import com.example.library.exceptions.RequiredObjectIsNullException;
import com.example.library.exceptions.ResourceNotFoundException;
import com.example.library.mapper.Mapper;
import com.example.library.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PublisherService {


    @Autowired
    public PublisherRepository repository;


    public List<PublisherResponseDTO> getAll(){

        return Mapper.parseListObjects(repository.findAll(), PublisherResponseDTO.class);
    }

    @Transactional
    public Publisher findById(Long id){

        return repository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("nao foi encontrado"));
    }

    @Transactional
    public Publisher createPublisher(Publisher publisher){

        if (publisher == null) throw new RequiredObjectIsNullException("Nao pode ser null");

        return repository.save(publisher);
    }
}
