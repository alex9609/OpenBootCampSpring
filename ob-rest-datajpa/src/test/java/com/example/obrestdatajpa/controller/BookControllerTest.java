package com.example.obrestdatajpa.controller;

import com.example.obrestdatajpa.entities.Book;
import io.swagger.models.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import javax.xml.transform.sax.SAXResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment  = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

    Logger log = LoggerFactory.getLogger(BookController.class);

    @LocalServerPort //Inyecta el puerto HTTP
    private int port;


    private TestRestTemplate testRestTemplate;
    @Autowired //Inyectando
    private RestTemplateBuilder restTemplateBuilder;

    @BeforeEach //Se ejecuta antes del test setup es antes de cada método
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:"+port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @DisplayName("Comprobar hola mundo test")
    @Test
    void hello() {
        //Respuesta HTTP
        ResponseEntity<String> response =
                testRestTemplate.getForEntity("/hola",String.class);

        //Estado de la respuesta
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(200,response.getStatusCodeValue());
        assertEquals("Hola mundo que tal vamos actualizando!!!",response.getBody());
    }
    @Test
    void findAll() {
        ResponseEntity<Book[]> response =
        testRestTemplate.getForEntity("/api/books",
                Book[].class);

        //No nos deja con List de java.util, por esto vamos a usar un array
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(200,response.getStatusCodeValue());

        List<Book>  books = Arrays.asList(response.getBody());
        log.info("Obteniendo el tamaño de la lista de libros " + books.size());
    }

    @Test
    void findOne() {
        //Respuesta HTTP
        ResponseEntity<String> response =
                testRestTemplate.getForEntity("/api/book/1",String.class);

        //Estado de la respuesta
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());

    }

    @Test
    void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "title": "Spring Boot",
                    "author": "James Goslin",
                    "pages": 500,
                    "price": 500.3,
                    "releaseDate": "2012-02-11",
                    "online": true
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json,headers);
        ResponseEntity<Book> response = testRestTemplate.exchange("/api/books",HttpMethod.POST,request,Book.class);

        Book result = response.getBody();

        assertEquals(1L,result.getId());
        assertEquals("Spring Boot",result.getTitle());
    }
}