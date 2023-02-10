package com.example.obspringdatajpa;

import javax.persistence.*;

@Entity
public class Coche {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="manufacturer")
    private String manufacturer;
    @Column(name = "model")
    private String model;
    @Column(name = "`year`")
    private Integer year;

    //Constructores


    public Coche() {
    }

    public Coche(Long id, String manufacturer, String model, Integer year) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
    }

    //Getter y Setter

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }


    //toString


    @Override
    public String toString() {
        return "Coche{" +
                "id=" + id +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                '}';
    }
}
