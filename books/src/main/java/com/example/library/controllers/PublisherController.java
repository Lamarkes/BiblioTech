package com.example.library.controllers;


import com.example.library.dto.publisher.PublisherResponseDTO;
import com.example.library.entities.Publisher;
import com.example.library.services.PublisherService;
import com.example.library.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publisher/v1")
@Tag(name = "Publishers", description = "Path for Publishers")
@RequiredArgsConstructor
public class PublisherController {



    final PublisherService publisherService;

    @Operation(summary = "Get all publishers",
            description = "Get all publishers from database")
    @ApiResponses(value = @ApiResponse(
            responseCode = "200",description = "OK"
    ))
    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<List<PublisherResponseDTO>> getAllPublisher(){

        return ResponseEntity.ok().body(publisherService.getAll());
    }

    @Operation(summary = "Save a publisher",
            description = "Save a publisher in database")
    @ApiResponses(value = @ApiResponse(
            responseCode = "201",description = "CREATED"
    ))
    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<Publisher> save(@RequestBody Publisher publisher){

        return ResponseEntity.ok().body(publisherService.createPublisher(publisher));
    }
}
