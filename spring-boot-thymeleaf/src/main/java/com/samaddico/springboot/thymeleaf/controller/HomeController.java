/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.samaddico.springboot.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author addico
 */
@Controller
public class HomeController {
    
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    
    @RequestMapping("/about")
    public String about(){
        return "about";
    }
    
    @RequestMapping("/contact")
    public String contact(){
        return "contact";
    }
}
