package com.example.ejecicios.controller;

import com.example.ejecicios.repository.LaptopRepository;
import com.example.ejecicios.entity.Laptop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
public class LaptopController {

    Logger logger = LoggerFactory.getLogger(LaptopController.class);

    private LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @GetMapping("/api/laptops")
    public ResponseEntity<List<Laptop>> findAll(){
        return ResponseEntity.ok(laptopRepository.findAll());
    }

    @PostMapping("/api/laptop")
    public ResponseEntity<Laptop> saveLaptop(@RequestBody Laptop laptop){
        String message = "";
        if (laptop.getId() != null){
            logger.warn("Trying to create a Laptop with id");
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(laptopRepository.save(laptop));
    }

    @GetMapping("/api/laptop/{id}")
    public ResponseEntity<Laptop> findOneById(@PathVariable Long id){
        Optional<Laptop> laptop = laptopRepository.findById(id);
       return laptop.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("api/laptop")
    public ResponseEntity<Laptop> updateLaptop(@RequestBody Laptop laptop){
        if (laptop.getId() == null){
            logger.warn("Id is null try to give more information");
            return ResponseEntity.badRequest().build();
        }else{
            if (laptopRepository.existsById(laptop.getId())){
                return ResponseEntity.ok(laptopRepository.save(laptop));
            }else{
                return ResponseEntity.notFound().build();
            }
        }
    }

    @DeleteMapping("api/laptop/{id}")
    public ResponseEntity<String> deleteLaoptopId(@PathVariable Long id){
        if (laptopRepository.existsById(id)){
            laptopRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }else{
            logger.warn("No existe registro para el id : " + id);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("api/laptops")
    public ResponseEntity<String> deleteAllLaptops(){
        if (laptopRepository.count() > 0){
            laptopRepository.deleteAll();
            return ResponseEntity.noContent().build();
        }else{
            logger.warn("No existen registros enl la base de datos");
            return ResponseEntity.notFound().build();
        }
    }
}
