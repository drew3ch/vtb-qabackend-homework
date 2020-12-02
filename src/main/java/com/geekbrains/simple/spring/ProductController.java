package com.geekbrains.simple.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// http://localhost:8189/app/products
@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id).get();
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product) {
        product.setId(null);
        return productRepository.save(product);
    }

    @GetMapping("/increase_cost_by_10/{id}")
    public void increaseCost(@PathVariable Long id) {
        Product p = productRepository.findById(id).get();
        p.setPrice(p.getPrice() + 10);
        productRepository.save(p);
    }

    @GetMapping("/count")
    public Long getProductsCount() {
        return productRepository.count();
    }

    @GetMapping("/cheap")
    public List<Product> getAllCheapProducts() {
        return productRepository.requestAllCheapProducts();
    }
}
