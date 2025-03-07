package com.example.library.dto;


import com.example.library.entities.Book;
import com.example.library.entities.Contact;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class PublisherResponseDTO {


    private String name;
    private String description;

    private List<Book> books;

    private List<Contact> contacts;

    public PublisherResponseDTO(){}


}
