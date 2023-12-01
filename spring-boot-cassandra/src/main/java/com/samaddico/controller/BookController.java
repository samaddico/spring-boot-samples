package com.samaddico.controller;

import com.samaddico.dto.BookRequest;
import com.samaddico.model.CreateBook;
import com.samaddico.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public ResponseEntity<List<CreateBook>> getBooks() {
        List<CreateBook> bookList = bookRepository.findAll();
        return ResponseEntity.status(200).body(bookList);
    }

    @PostMapping
    public ResponseEntity<CreateBook> createBook(@RequestBody BookRequest bookRequest) {

        CreateBook book = new CreateBook();
        book.setId(UUID.randomUUID());
        book.setTitle(bookRequest.getTitle());
        book.setDescription(bookRequest.getDescription());
        book.setAuthor(bookRequest.getAuthor());
        book.setYear(bookRequest.getYear());
        book.setDateCreated(LocalDateTime.now());

        book = bookRepository.save(book);
        return ResponseEntity.status(201).body(book);
    }
}
