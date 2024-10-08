package com.example.library.controllers;


import com.example.library.dto.PublisherResponseDTO;
import com.example.library.entities.Publisher;
import com.example.library.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/publisher/v1")
public class PublisherController {


    @Autowired
    private PublisherService publisherService;


    @GetMapping
    public ResponseEntity<List<PublisherResponseDTO>> getAllPublisher(){

        return ResponseEntity.ok().body(publisherService.getAll());
    }
}
