package com.samaddico.springboot.caching.service;


import com.samaddico.springboot.caching.model.Book;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;


@Component
@CacheConfig(cacheNames = { "book" })
public class BookService {


    @Cacheable(key = "#book.id")
    public Book put(Book book) {
        return book;
    }


    @Cacheable(key = "#book.id")
    public Book get(Book book) {
        return book;
    }

    @Cacheable(key = "#id")
    public Book get(String id) {
        return null;
    }

    @CacheEvict(key = "#id")
    public void delete(String id) {

    }

}
