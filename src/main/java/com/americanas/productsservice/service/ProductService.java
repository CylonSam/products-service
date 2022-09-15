package com.americanas.productsservice.service;

import com.americanas.productsservice.domain.Product;
import com.americanas.productsservice.exceptions.ProductNotFoundException;
import com.americanas.productsservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product getProductById(Long id) {
        Product product = repository.findById(id);
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
        Product productAlreadyExists = repository.findByName(product.getName());

        if (productAlreadyExists != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product already exists");
        }

        return repository.save(product);
    }

    public Product update(Product product) {
        return repository.save(product);
    }

    public void delete(Product product) {
        repository.delete(product);
    }

}
