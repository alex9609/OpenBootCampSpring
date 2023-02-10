package com.example.obrestdatajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication
public class ObRestDatajpaApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ObRestDatajpaApplication.class, args);
		BookRepository bookRepository = (BookRepository) context.getBean(BookRepository.class);

		//CRUD
		//Crear un libro
		Book book = new Book(null,"Cien años de soledad","Gabriel Garcia Marquez",
				150, 120.30,LocalDate.now(),true);
		Book book2 = new Book(null,"Guerra de mil dias","Gabriel Garcia Marquez",
				300, 300.30,LocalDate.now(),true);
		//Almacenar un libro
		bookRepository.save(book);
		bookRepository.save(book2);

		//Recuperar todos los libros
		bookRepository.findAll().forEach(System.out::println);
		//Borrar un libro
		bookRepository.deleteById(1L);

	}

}
