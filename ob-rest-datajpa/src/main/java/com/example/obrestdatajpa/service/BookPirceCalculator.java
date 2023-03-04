package com.example.obrestdatajpa.service;

import com.example.obrestdatajpa.entities.Book;

public class BookPirceCalculator {

    public double calculatePrice(Book book){
        double price = book.getPrice();

        if (book.getPages() > 1000){
            price += 5;
        }

        price += 299.5;
        return price;
    }
}
