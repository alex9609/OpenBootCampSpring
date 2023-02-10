package org.example;

public class GestorFacturas {

    Calculadora calculadora;
    private String nombre;

    public GestorFacturas(Calculadora calculadora,String nombre){
        System.out.println("Ejecutando constructor de facturas");
        this.calculadora = calculadora;
        this.nombre = nombre;
    }
}

