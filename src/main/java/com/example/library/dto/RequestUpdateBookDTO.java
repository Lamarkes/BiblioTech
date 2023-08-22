package com.example.library.dto;

public class RequestUpdateBookDTO {

    private String title;
    private Integer year;
    private Double value;
    private String PublishingCompany;

    public RequestUpdateBookDTO(){
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getPublishingCompany() {
        return PublishingCompany;
    }

    public void setPublishingCompany(String publishingCompany) {
        PublishingCompany = publishingCompany;
    }
}
