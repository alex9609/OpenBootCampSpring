package com.example.springdeploy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class SpringDeployApplicationTests {

	@Test
	void contextLoads() {
		System.getenv().forEach((x,y) -> System.out.println(x + " = " +  y));
	}

}
