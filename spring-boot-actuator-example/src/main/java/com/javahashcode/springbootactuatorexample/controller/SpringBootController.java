/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javahashcode.springbootactuatorexample.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author addico
 */
@RestController
public class SpringBootController {

    @GetMapping("/hello")
    public Map hello() {
        Map<String, String> response = new HashMap<>();
        response.put("name", "Samuel");
        response.put("age", "25");
        response.put("message", "Hello world");
        return response;
    }

}
