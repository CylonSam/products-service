package com.americanas.productsservice.controller;

import com.americanas.productsservice.domain.Product;
import com.americanas.productsservice.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;

public class ProductController {

    private ProductService service;

    @GetMapping("/{id}")
    public Product getProductById(Long id) {
        return service.getProductById(id);
    }
}
