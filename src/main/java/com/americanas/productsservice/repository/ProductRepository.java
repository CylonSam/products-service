package com.americanas.productsservice.repository;

import com.americanas.productsservice.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {

    Product findByName(String name);
}
