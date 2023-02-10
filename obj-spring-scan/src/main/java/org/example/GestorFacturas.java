package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GestorFacturas {


    Calculadora calculadora;
    private String nombre = "Calculator 3000";

    public GestorFacturas(Calculadora calculadora){
        System.out.println("Ejecutando constructor de facturas");
        this.calculadora = calculadora;
    }
}

