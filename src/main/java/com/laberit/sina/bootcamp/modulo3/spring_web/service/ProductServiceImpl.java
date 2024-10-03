package com.laberit.sina.bootcamp.modulo3.spring_web.service;

import com.laberit.sina.bootcamp.modulo3.spring_web.model.Product;
import com.laberit.sina.bootcamp.modulo3.spring_web.model.ProductDetail;
import com.laberit.sina.bootcamp.modulo3.spring_web.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void addProduct(Product product) {
        for (ProductDetail detail : product.getProductDetail()) {
            detail.setProduct(product);
        }
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Long id, Product product) {
        if (productRepository.existsById(id)) {
            for (ProductDetail detail : product.getProductDetail()) {
                detail.setProduct(product);
            }
            product.setId(id);
            productRepository.save(product);
        }
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}