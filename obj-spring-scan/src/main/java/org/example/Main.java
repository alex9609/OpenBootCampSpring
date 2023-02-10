package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");


        //Ejemplo 1:
        Calculadora calculadora = (Calculadora) context.getBean("calculadora");
        /*

        //OJO aquí se recupera el mismo objeto
        Calculadora calculadora2 = (Calculadora) context.getBean("calculadora");

        String texto = calculadora.holaMundo();
        System.out.println(texto);
        System.out.println(calculadora2.holaMundo());
        */

        //Ejemplo 2:

        GestorFacturas gestorFacturas = (GestorFacturas) context.getBean("gestorFacturas");
        System.out.println(gestorFacturas.calculadora.holaMundo());
    }
}