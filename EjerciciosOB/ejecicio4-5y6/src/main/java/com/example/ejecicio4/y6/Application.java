package com.example.ejecicio4.y6;

import com.example.ejecicio4.y6.entity.Laptop;
import com.example.ejecicio4.y6.repository.LaptopRepository;
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
