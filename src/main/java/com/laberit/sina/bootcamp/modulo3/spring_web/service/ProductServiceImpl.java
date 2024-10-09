package com.laberit.sina.bootcamp.modulo3.spring_web.service;

import com.laberit.sina.bootcamp.modulo3.spring_web.dto.ProductDTO;
import com.laberit.sina.bootcamp.modulo3.spring_web.enumeration.Category;
import com.laberit.sina.bootcamp.modulo3.spring_web.model.Product;
import com.laberit.sina.bootcamp.modulo3.spring_web.model.ProductDetail;
import com.laberit.sina.bootcamp.modulo3.spring_web.repository.ProductRepository;
import com.laberit.sina.bootcamp.modulo3.spring_web.utils.EnumUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EntityManager entityManager;


    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        fetchProductDetails(products.getContent());
        return products;
    }

    @Override
    public Page<Product> getAllProductsByCategory(Category category, Pageable pageable) {
        Page<Product> products = productRepository.findByCategory(category, pageable);
        fetchProductDetails(products.getContent());
        return products;
    }

    @Override
    public Page<Product> getAllProductsFilterByName(String name, String lang, Pageable pageable) {
        Page<Product> products = productRepository.findAllFilterByName(name, lang, pageable);
        fetchProductDetails(products.getContent());
        return products;
    }

    @Override
    public Page<Product> getAllProductsByCategoryFilterByName(Category category, String name, String lang, Pageable pageable) {
        Page<Product> products = productRepository.findByCategoryFilterByName(category, name, lang, pageable);
        fetchProductDetails(products.getContent());
        return products;
    }

    public void fetchProductDetails(List<Product> products) {
        List<Long> productIds = products.stream().map(Product::getId).collect(Collectors.toList());
        List<Product> productsWithDetails = productRepository.findAllById(productIds);
        productsWithDetails.forEach(product -> product.getProductDetail().size()); // Trigger lazy loading by accessing the size
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
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
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<ProductDTO> getProductDTO(String category, String lang, String name, Pageable pageable) {
        Page<Product> products;
        if (category != null && EnumUtils.isValidCategory(category)) {
            Category categoryEnum = Category.valueOf(category.toUpperCase());
            if (name != null && !name.isEmpty()) {
                products = getAllProductsByCategoryFilterByName(categoryEnum, name, lang, pageable);
            } else {
                products = getAllProductsByCategory(categoryEnum, pageable);
            }
        } else {
            if (name != null && !name.isEmpty()) {
                products = getAllProductsFilterByName(name, lang, pageable);
            } else {
                products = getAllProducts(pageable);
            }
        }
        return products.map(product -> new ProductDTO(product, lang));
    }
}