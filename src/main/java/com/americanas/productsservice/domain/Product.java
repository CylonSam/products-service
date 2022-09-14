package com.americanas.productsservice.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue
    private String id;
    @NonNull
    @Column(name = "name", nullable = false)
    private String name;
    @NonNull
    @Column(name = "price", nullable = false)
    private double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product() {

    }
}
