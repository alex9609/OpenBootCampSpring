package com.example.ejecicios;

import com.example.ejecicios.repository.LaptopRepository;
import com.example.ejecicios.entity.Laptop;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);
		LaptopRepository laptopRepository = (LaptopRepository) context.getBean(LaptopRepository.class);

		Laptop laptop1 = new Laptop(null,16,256,"i7","15");
		Laptop laptop2 = new Laptop(null,8,550,"i5","14");

		laptopRepository.save(laptop2);
		laptopRepository.save(laptop1);
 	}

}
