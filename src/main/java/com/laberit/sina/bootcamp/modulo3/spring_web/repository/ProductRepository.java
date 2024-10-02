package com.laberit.sina.bootcamp.modulo3.spring_web.repository;

import com.laberit.sina.bootcamp.modulo3.spring_web.model.Product;
import com.laberit.sina.bootcamp.modulo3.spring_web.model.ProductDetail;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private final List<Product> products = new ArrayList<>();
    private Long lastId = 0L;

    public ProductRepository() {
        addProduct(new Product("GPU001", 289.99, 10L, new ProductDetail[]{
                new ProductDetail(2L, "Tarjeta Gráfica MSI GeForce RTX 4060", "VENTUS 2X BLACK OC 8GB GDDR6 DLSS3", "es"),
                new ProductDetail(1L, "Graphic Card MSI GeForce RTX 4060", "VENTUS 2X BLACK OC 8GB GDDR6 DLSS3", "en")}));
        addProduct(new Product("GPU002", 689.0, 20L));
        addProduct(new Product("GPU003", 1511.29, 30L));
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
