package com.example.library.enums;

public enum GenreBook {

    FANTASY("fantasy"),
    MYSTERY("mystery"),
    HORROR("horror"),
    FABLE("fable"),
    FICTION("fiction"),
    NEGOTIATION("negotiation"),
    ADVENTURE("adventure"),
    ROMANCE("romance"),
    LITERATURE("literature"),
    ACTION("action"),
    PHILOSOPHY("philosophy");


    private final String message;


    GenreBook(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }
}
