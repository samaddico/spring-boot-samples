package com.samaddico.springboot.caching.controller;

import com.samaddico.springboot.caching.form.JsonResponse;
import com.samaddico.springboot.caching.model.Book;
import com.samaddico.springboot.caching.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RedisController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/put",method = RequestMethod.POST)
    public Object add(@RequestBody Book session){
        Book book = bookService.put(session);
        JsonResponse response = new JsonResponse();
        response.setStatus(true);
        response.setData(book);
        return response;
    }

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public Object get(@RequestParam("id") String id) {
        Book result = bookService.get(id);
        JsonResponse response = new JsonResponse();
        response.setStatus(true);
        response.setData(result);
        return response;
    }

    @RequestMapping(value = "/evict",method = RequestMethod.GET)
    public Object remove(@RequestParam("id") String id){
        Book book = new Book();
        book.setId(id);
        bookService.delete(id);
        JsonResponse response = new JsonResponse();
        response.setStatus(true);
        return response;
    }

}
