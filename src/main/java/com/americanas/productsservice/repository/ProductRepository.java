package com.americanas.productsservice.repository;

import com.americanas.productsservice.domain.Product;

public interface ProductRepository {

    public Product findByName(String name);
}
