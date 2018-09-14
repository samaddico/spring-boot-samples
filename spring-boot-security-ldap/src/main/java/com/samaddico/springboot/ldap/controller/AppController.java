/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samaddico.springboot.ldap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author addico
 */
@Controller
public class AppController {
    
    @RequestMapping(value = "/login")
    public String login() {
        return "signin";
    }
    
    @RequestMapping(value = "/home")
    public String home() {
        return "index";
    }
    
    @RequestMapping(value = "/")
    public String home1() {
        return "index";
    }
}
