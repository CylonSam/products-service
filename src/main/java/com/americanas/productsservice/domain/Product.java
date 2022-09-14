package com.americanas.productsservice.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class Product {

    @Id
    private String Id;
    @NonNull
    private String name;
    @NonNull
    private double price;

    public Product(String id, String name, double price) {
        Id = id;
        this.name = name;
        this.price = price;
    }
}
