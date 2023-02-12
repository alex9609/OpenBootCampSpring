package com.example.ejecicio4.y6.controller;

import com.example.ejecicio4.y6.entity.Laptop;
import com.example.ejecicio4.y6.repository.LaptopRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class LaptopController {

    private LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @GetMapping("/api/laptops")
    public ResponseEntity<List<Laptop>> findAll(){
        return ResponseEntity.ok(laptopRepository.findAll());
    }

    @PostMapping("/api/laptop")
    public ResponseEntity<String> saveLaptop(@RequestBody Laptop laptop){
        String message = "";
        try{
            laptopRepository.save(laptop);
            message = "Created";
        }catch (Exception e){
            message = "Server error";
        }
        return message.equals("Created") ? ResponseEntity.ok(message) : ResponseEntity.internalServerError().body(message);
    }
}
