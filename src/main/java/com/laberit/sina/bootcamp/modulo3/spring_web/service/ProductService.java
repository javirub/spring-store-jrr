package com.laberit.sina.bootcamp.modulo3.spring_web.service;

import com.laberit.sina.bootcamp.modulo3.spring_web.dto.ProductDTO;
import com.laberit.sina.bootcamp.modulo3.spring_web.enumeration.Category;
import com.laberit.sina.bootcamp.modulo3.spring_web.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Product getProductById(Long id);

    void addProduct(Product product);

    void updateProduct(Long id, Product product);

    void deleteProduct(Long id);

    Page<Product> getAllProducts(Pageable pageable);

    Page<Product> getAllProductsByCategory(Category category, Pageable pageable);

    Page<Product> getAllProductsFilterByName(String name, String lang, Pageable pageable);

    Page<Product> getAllProductsByCategoryFilterByName(Category category, String name, String lang, Pageable pageable);

    Page<ProductDTO> getProductDTO(String category, String lang, String name, Pageable pageable);
}