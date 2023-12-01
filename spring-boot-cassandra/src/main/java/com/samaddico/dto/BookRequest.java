package com.samaddico.dto;


import lombok.Data;

@Data
public class BookRequest {

    private String title;
    private String description;
    private String author;
    private int year;

}
