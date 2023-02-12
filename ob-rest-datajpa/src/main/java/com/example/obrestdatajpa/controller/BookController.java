package com.example.obrestdatajpa.controller;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    //CRUD sobre la entidad Book

    //Buscar todos los libros
    @GetMapping("/api/books")
    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    //Buscar un solo libro en base a su id

    @GetMapping("api/book/{id}")
    public ResponseEntity<Book> findOne(@PathVariable  Long id){
        Optional<Book> bookOpt = bookRepository.findById(id);


        return  bookOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

        //return bookOpt.isPresent() ? ResponseEntity.ok(bookOpt.get()) : ResponseEntity.notFound().build();

        //return bookOpt.orElse(null);
    }
    //Crear un nuevo libro
    @PostMapping("api/books")
    public ResponseEntity<String> create(@RequestBody Book book){
        String message = "";
        try {
            bookRepository.save(book);
            message = "Created";
        }catch (Exception e){
            message = "A problem has ocurred";
        }
        return ResponseEntity.ok(message);
    }

    //Actualizar un libro existente en base de datos

    //Borrar un libro en base de datos


}
