package com.laberit.sina.bootcamp.modulo3.spring_web.service;

import com.laberit.sina.bootcamp.modulo3.spring_web.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    void addProduct(Product product);
    void updateProduct(Long id, Product product);
    void deleteProduct(Long id);
}