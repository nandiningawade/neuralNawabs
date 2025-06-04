package com.example.NeuralNawab.service;

import com.example.NeuralNawab.model.Product;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    public List<Product>  products = new ArrayList<>(Arrays.asList(
            new Product(101, "iPhone",50000),
            new Product(102, "Android",30000),
            new Product(103, "MacBook",250000)
    ));

    public List<Product> getProducts() {
        return products;
    }

    public Product getProductById(int prodId) {
        return products.stream()
                .filter(p -> p.getProdId() == prodId)
                .findFirst()
                .orElse(new Product(100, "No Item",0));
    }

    public List<Product> addProduct(Product prod) {
        products.add(prod);
        return products;
    }

    public List<Product> addProducts(List<Product> prods) {
        products.addAll(prods);
        return products;
    }

    public List<Product> deleteProduct(int prodId) {
        products.removeIf(p -> p.getProdId() == prodId);
        return products;
    }

    public List<Product> updateProduct(Product prod) {
        Product reqProd = getProductById(prod.getProdId());
        int index = products.indexOf(reqProd);
        if(index != -1) {
            products.set(index, prod);
        }
        return products;
    }

}
