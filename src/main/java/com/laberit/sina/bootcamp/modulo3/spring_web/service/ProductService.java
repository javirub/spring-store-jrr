package com.laberit.sina.bootcamp.modulo3.spring_web.service;

import com.laberit.sina.bootcamp.modulo3.spring_web.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>();
    private Long lastId = 0L;

    // Constructor (añade productos de ejemplo)
    public ProductService() {
        addProduct(new Product(null, "GPU001", 289.99, 10L, null));
        addProduct(new Product(null, "GPU002", 689.0, 20L, null));
        addProduct(new Product(null, "GPU003", 1511.29, 30L, null));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void addProduct(Product product) {
        product.setId(++lastId);
        products.add(product);
    }

    public void updateProduct(Long id, Product product) {
        products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .ifPresent(p -> {
                    p.setCode(product.getCode());
                    p.setPrice(product.getPrice());
                    p.setStock(product.getStock());
                    p.setProductDetail(product.getProductDetail());
                });
    }

    public void deleteProduct(Long id) {
        products.removeIf(product -> product.getId().equals(id));
    }
}
