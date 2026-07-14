package com.example.library.enums;

import lombok.Getter;

@Getter
public enum Format {

    PHYSICAL("physical"),
    DIGITAL("digital");

    private final String message;

    Format(String msg){
        this.message = msg;
    }
}
