package com.samaddico.model;


import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table("book")
public class CreateBook {

    @PrimaryKey
    private UUID id;

    private String title;

    private String description;

    private String author;

    private int year;

    private LocalDateTime dateCreated;

}
