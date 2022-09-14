package com.americanas.productsservice.repository;

import com.americanas.productsservice.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    Product findByName(String name);
}
