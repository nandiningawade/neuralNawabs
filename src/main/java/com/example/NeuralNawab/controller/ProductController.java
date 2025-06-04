package com.example.NeuralNawab.controller;

import com.example.NeuralNawab.model.Product;
import com.example.NeuralNawab.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping("/products")
    public List<Product> getProducts() {
        return service.getProducts();
    }

    @GetMapping("/products/{prodId}")
    public Product getProductById(@PathVariable int prodId) {
        return service.getProductById(prodId);
    }

    @PostMapping("/product")
    public List<Product> addProduct(@RequestBody Product prod) {
        return service.addProduct(prod);
    }

    @PostMapping("/products")
    public List<Product> addProducts(@RequestBody List<Product> prods) {
        return service.addProducts(prods);
    }

    @DeleteMapping("/products/{prodId}")
    public List<Product> deleteProduct(@PathVariable int prodId) {
        return service.deleteProduct(prodId);
    }

    @PutMapping("/products")
    public List<Product> updateProduct(@RequestBody Product prod) {
        return service.updateProduct(prod);
    }
}
