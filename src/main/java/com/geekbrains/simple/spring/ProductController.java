package com.geekbrains.simple.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
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
    //http://localhost:8189/app/products/filtered?min_price=100
    @GetMapping("/filtered")
    public List<Product> getProductsPriceGreater(Integer min_price) {
        return productRepository.findByPriceGreaterThan(min_price);
    }
    //http://localhost:8189/app/products/delete/1
    @PostMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
    //http://localhost:8189/app/products/2/change_title?title=BREAD
    @Transactional
    @PutMapping("/{id}/change_title")
    public void changeTitleByid(@PathVariable Long id, @RequestParam String title) {
        productRepository.updateTitleById(title, id);
    }
}
