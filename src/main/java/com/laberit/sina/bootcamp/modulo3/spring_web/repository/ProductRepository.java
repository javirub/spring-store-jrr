package com.laberit.sina.bootcamp.modulo3.spring_web.repository;

import com.laberit.sina.bootcamp.modulo3.spring_web.enumeration.Category;
import com.laberit.sina.bootcamp.modulo3.spring_web.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);
}