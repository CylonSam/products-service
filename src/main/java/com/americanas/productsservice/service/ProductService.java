package com.americanas.productsservice.service;

import com.americanas.productsservice.domain.Product;
import com.americanas.productsservice.exceptions.ProductNotFoundException;
import com.americanas.productsservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product getProductById(Long id) {
        Product product = repository.getReferenceById(String.valueOf(id));
        if (product != null) {
            return product;
        }
        throw new ProductNotFoundException(HttpStatus.NOT_FOUND, "Product not found");
    }

    public Product getProductByName(String name) {
        Product product = repository.findByName(name);
        if (product != null) {
            return product;
        }
        throw new ProductNotFoundException(HttpStatus.NOT_FOUND, "Product not found");
    }

    public Product create(Product product) {
        return repository.save(product);
    }

}
