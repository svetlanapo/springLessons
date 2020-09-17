package ru.spo;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue()
    @Column
    private long id;
    private String name;
    private double price;

    public Product(long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
