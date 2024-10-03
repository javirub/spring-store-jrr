package com.laberit.sina.bootcamp.modulo3.spring_web.initializer;

import com.laberit.sina.bootcamp.modulo3.spring_web.model.Product;
import com.laberit.sina.bootcamp.modulo3.spring_web.model.ProductDetail;
import com.laberit.sina.bootcamp.modulo3.spring_web.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        Product product1 = new Product("GPU001", 289.99, 10L);
        ProductDetail detail1 = new ProductDetail("Tarjeta Gráfica MSI GeForce RTX 4060", "VENTUS 2X BLACK OC 8GB GDDR6 DLSS3", "es", product1);
        ProductDetail detail2 = new ProductDetail("Graphic Card MSI GeForce RTX 4060", "VENTUS 2X BLACK OC 8GB GDDR6 DLSS3", "en", product1);
        product1.setProductDetail(List.of(detail1, detail2));
        productRepository.save(product1);

        Product product2 = new Product("GPU002", 689.0, 20L);
        productRepository.save(product2);

        Product product3 = new Product("GPU003", 1511.29, 30L);
        productRepository.save(product3);
    }
}