package com.example.library.entities;

import com.example.library.dto.BookDTO;
import com.example.library.dto.RequestBookDTO;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

@Entity
@Table(name = "tb_book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(name = "book_year")
    private Integer year;
    private String author;
    @Column(name ="book_value")
    private Double value;
    private Integer numPages;
    private String genre;
    private Double rating;
    @Column(columnDefinition = "boolean default true", name = "book_active")
    private Boolean active;
    private String PublishingCompany;
    @Column(columnDefinition = "TEXT")
    private String description;

    public Book(){}

    public Book(Long id, String title, Integer year, String author, Double value, Integer numPages, String genre, Double rating, String publishingCompany, String description) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.author = author;
        this.value = value;
        this.numPages = numPages;
        this.genre = genre;
        this.rating = rating;
        this.PublishingCompany = publishingCompany;
        this.description = description;
    }

    public Book (BookDTO bookDTO){
        BeanUtils.copyProperties(bookDTO,this);
    }
    public Book (RequestBookDTO request){
        BeanUtils.copyProperties(request,this);
        this.active = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Integer getNumPages() {
        return numPages;
    }

    public void setNumPages(Integer numPages) {
        this.numPages = numPages;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getPublishingCompany() {
        return PublishingCompany;
    }

    public void setPublishingCompany(String publishingCompany) {
        PublishingCompany = publishingCompany;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
