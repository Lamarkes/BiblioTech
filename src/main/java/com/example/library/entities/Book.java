package com.example.library.entities;

import jakarta.persistence.*;

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
    private Long value;
    private Integer numPages;
    private String genre;
    private Double rating;
    @Column(name = "book_active")
    private Boolean active;
    private String PublishingCompany;
    @Column(columnDefinition = "TEXT")
    private String description;

    public Book(){}

    public Book(Long id, String title, Integer year, String author, Long value, Integer numPages, String genre, Double rating, Boolean active, String publishingCompany, String description) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.author = author;
        this.value = value;
        this.numPages = numPages;
        this.genre = genre;
        this.active = active;
        this.rating = rating;
        PublishingCompany = publishingCompany;
        this.description = description;
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

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
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
