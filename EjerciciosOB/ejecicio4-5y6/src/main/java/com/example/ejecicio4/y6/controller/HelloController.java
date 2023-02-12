package com.example.ejecicio4.y6.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/api/saludar")
    public String saludar(){
        return "Hello world";
    }
}
