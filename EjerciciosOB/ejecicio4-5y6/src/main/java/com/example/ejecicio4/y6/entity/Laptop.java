package com.example.ejecicio4.y6.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Laptop")
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer ram;
    private Integer ssd;
    private String processor;
    private String pulgadas;

    public Laptop(Long id, Integer ram, Integer ssd, String processor, String pulgadas) {
        this.id = id;
        this.ram = ram;
        this.ssd = ssd;
        this.processor = processor;
        this.pulgadas = pulgadas;
    }

    public Laptop() {
    }

    public Long getId() {
        return id;
    }

    public Integer getRam() {
        return ram;
    }

    public Integer getSsd() {
        return ssd;
    }

    public String getProcessor() {
        return processor;
    }

    public String getPulgadas() {
        return pulgadas;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "id=" + id +
                ", ram=" + ram +
                ", ssd=" + ssd +
                ", processor='" + processor + '\'' +
                ", pulgadas='" + pulgadas + '\'' +
                '}';
    }
}
