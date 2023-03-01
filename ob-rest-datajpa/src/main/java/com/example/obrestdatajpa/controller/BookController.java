package com.example.obrestdatajpa.controller;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repository.BookRepository;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    private BookRepository bookRepository;
    private final Logger log = LoggerFactory.getLogger(BookController.class);

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
    public ResponseEntity<Book> create(@RequestBody Book book){
        String message = "";
       if (book.getId() != null){
           log.warn("trying to create a book with id");
           return ResponseEntity.badRequest().build();
       }
       return ResponseEntity.ok(bookRepository.save(book));
    }

    //Actualizar un libro existente en base de datos
    @PutMapping("api/book")
    public ResponseEntity<Book> update(@RequestBody Book  book){
        if (book.getId() == null){
            log.warn("Book have an id invalid");
            return ResponseEntity.badRequest().build();
        }else{
            if (bookRepository.existsById(book.getId())){
                return ResponseEntity.ok(bookRepository.save(book));
            }else{
                log.warn("Book Not found");
                return ResponseEntity.notFound().build();
            }
        }

    }

    //Borrar un libro en base de datos
    @DeleteMapping("/api/book/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        if(!bookRepository.existsById(id)){
            log.warn("Trying to delete a non existing book");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    //Borrar todos los libros
    @ApiIgnore //Ignorar este método para que no aparezca en la documentación de la api Swagger
    @DeleteMapping("/api/books")
    @ApiOperation("Eliminando todos los libros")
    public ResponseEntity<Book> deleteAll(){
       if (bookRepository.count() == 0){
           log.warn("No information in DB");
           return ResponseEntity.notFound().build();
       }
       log.info("REST Request Delete all books");
       bookRepository.deleteAll();
       return ResponseEntity.noContent().build();
    }

}
