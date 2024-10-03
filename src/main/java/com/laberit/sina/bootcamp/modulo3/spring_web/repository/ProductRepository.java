package com.laberit.sina.bootcamp.modulo3.spring_web.repository;

import com.laberit.sina.bootcamp.modulo3.spring_web.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}