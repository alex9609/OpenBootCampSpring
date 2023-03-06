package com.example.ejecicios.controller;

import com.example.ejecicios.entity.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    Logger log = LoggerFactory.getLogger(LaptopController.class);

    @LocalServerPort
    private  int port;

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;


    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:"+port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);

    }

    @Test
    void findAll() {
        ResponseEntity<Laptop[]> response =
                testRestTemplate.getForEntity("/api/laptops",
                        Laptop[].class);

        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(200,response.getStatusCodeValue());

        List<Laptop> laptops = Arrays.asList(response.getBody());
        log.info("Obteniendo el tamaño de la lista de laptops " + laptops.size());
    }

    @Test
    void saveLaptop() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "ram": 32,
                    "ssd": 1000,
                    "processor": "i7",
                    "pulgadas": "15"
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json,headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptop",HttpMethod.POST,request,Laptop.class);

        assertEquals(1L,response.getBody().getId());
        assertEquals("i7",response.getBody().getProcessor());
    }

    @Test
    void findOneById() {
        ResponseEntity<String> response =
            testRestTemplate.getForEntity("/api/laptop/1",String.class);

        //Estado de la respuesta
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        assertEquals(404,response.getStatusCodeValue());
    }

    @Test
    void updateLaptop() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "id":1,
                    "ram": 32,
                    "ssd": 1000,
                    "processor": "i7",
                    "pulgadas": "15"
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json,headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptop",HttpMethod.PUT,request,Laptop.class);


        log.info(" -> " + response);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    void deleteLaoptopId() {
        testRestTemplate.delete("/api/laptop/1",Laptop.class);

    }

    @Test
    void deleteAllLaptops() {
        testRestTemplate.delete("/api/laptops",Laptop.class);

    }
}