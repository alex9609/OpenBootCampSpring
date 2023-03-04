package com.example.obrestdatajpa.service;

import com.example.obrestdatajpa.entities.Book;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookPirceCalculatorTest {

    Logger log = LoggerFactory.getLogger(BookPirceCalculatorTest.class);

    @Test
    void calculatePriceTest() {
        // Configuración de la prueba
        Book book1 = new Book(12l,"100 pagins","Gabriel",1200,49.99, LocalDate.now(),true);
        BookPirceCalculator calculator = new BookPirceCalculator();

        //Comprobaciones de que todo esta funcionando.
        double price = calculator.calculatePrice(book1);
        log.info("Comprobando el precio : " + price);

        //Comprobaciones de assertions
        assertTrue(price > 0);
        assertEquals(price,354.49);
    }

    @Test
    void calculatePriceTestNull() {
        // Configuración de la prueba
        Book book1 = null;
        BookPirceCalculator calculator = new BookPirceCalculator();

        //Comprobaciones de que todo esta funcionando.

        assertThrows(NullPointerException.class,() -> {
            calculator.calculatePrice(book1);
        });
    }
}