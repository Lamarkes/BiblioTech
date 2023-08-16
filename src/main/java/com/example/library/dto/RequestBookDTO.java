package com.example.library.dto;

import org.springframework.lang.NonNull;

public class RequestBookDTO {


    private String title;
    private Integer year;
    private String author;
    private Long value;
    private Integer numPages;
    private String genre;
    private Double rating;
    private Boolean active;
    private String PublishingCompany;
    private String description;


    public RequestBookDTO() {
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
}
