package com.americanas.productsservice.service;

import com.americanas.productsservice.domain.Product;
import com.americanas.productsservice.exceptions.ProductNotFoundException;
import com.americanas.productsservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product getProductById(String id) {
        Product product = repository.getReferenceById(id);
        if (product != null) {
            return product;
        }
        throw new ProductNotFoundException(HttpStatus.NOT_FOUND, "Product not found");
    }

}
