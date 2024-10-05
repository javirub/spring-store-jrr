package com.laberit.sina.bootcamp.modulo3.spring_web.service;

import com.laberit.sina.bootcamp.modulo3.spring_web.enumeration.Category;
import com.laberit.sina.bootcamp.modulo3.spring_web.model.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id);

    void addProduct(Product product);

    void updateProduct(Long id, Product product);

    void deleteProduct(Long id);

    List<Product> getAllProductsWithDetail();

    List<Product> getAllProductsByCategoryWithDetail(Category category);

    List<Product> getAllProductsWithDetailFilterByName(String name, String lang);

    List<Product> getAllProductsByCategoryWithDetailFilterByName(Category category, String name, String lang);
}