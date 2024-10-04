package com.laberit.sina.bootcamp.modulo3.spring_web.service;

import com.laberit.sina.bootcamp.modulo3.spring_web.enumeration.Category;
import com.laberit.sina.bootcamp.modulo3.spring_web.model.Product;
import com.laberit.sina.bootcamp.modulo3.spring_web.model.ProductDetail;
import com.laberit.sina.bootcamp.modulo3.spring_web.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProductsWithDetail() {
        return productRepository.findAllWithDetail();
    }

    @Override
    public List<Product> getAllProductsByCategoryWithDetailFilterByName(Category category, String name, String lang) {
        name = "%" + name + "%";
        return productRepository.findByCategoryWithDetailFilterByName(category, name, lang);
    }

    @Override
    public List<Product> getAllProductsWithDetailFilterByName(String name, String lang) {
        name = "%" + name + "%";
        return productRepository.findAllWithDetailFilterByName(name, lang);
    }

    @Override
    public List<Product> getAllProductsByCategoryWithDetail(Category category) {
        return productRepository.findByCategoryWithDetail(category);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Product> getByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    @Transactional
    public void addProduct(Product product) {
        // Ensure each ProductDetail is associated with the Product
        if (product.getProductDetail() != null) {
            for (ProductDetail detail : product.getProductDetail()) {
                detail.setProduct(product);
            }
        }
        // Use EntityManager to merge the Product entity
        entityManager.merge(product);
    }

    @Override
    @Transactional
    public void updateProduct(Long id, Product product) {
        Product existingProduct = entityManager.find(Product.class, id);
        if (existingProduct == null) {
            throw new EntityNotFoundException("Product not found with id: " + id);
        }
        if (product.getProductDetail() != null) {
            existingProduct.getProductDetail().clear();
            for (ProductDetail detail : product.getProductDetail()) {
                detail.setProduct(existingProduct);
                existingProduct.getProductDetail().add(detail);
            }
        }
        existingProduct.setCode(product.getCode());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStock(product.getStock());
        entityManager.merge(existingProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}