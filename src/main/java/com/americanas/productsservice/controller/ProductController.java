package com.americanas.productsservice.controller;

import com.americanas.productsservice.domain.Product;
import com.americanas.productsservice.exceptions.ProductNotFoundException;
import com.americanas.productsservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product updateById(@RequestBody Product productWithNewInfo, @PathVariable Long id) {
        var product = productService.getProductById(id);

        if (product == null) {
            throw new ProductNotFoundException(HttpStatus.BAD_REQUEST, "Product not found");
        }

        product.setName(productWithNewInfo.getName());
        product.setPrice(productWithNewInfo.getPrice());

        return productService.update(product);
    }
}
