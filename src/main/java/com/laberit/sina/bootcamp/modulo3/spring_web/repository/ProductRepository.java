package com.laberit.sina.bootcamp.modulo3.spring_web.repository;

import com.laberit.sina.bootcamp.modulo3.spring_web.enumeration.Category;
import com.laberit.sina.bootcamp.modulo3.spring_web.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.category = :category ORDER BY p.id")
    Page<Product> findByCategory(@Param("category") Category category, Pageable pageable);

    @Query("SELECT p FROM Product p ORDER BY p.id")
    Page<Product> findAll(Pageable pageable);

    @Query("SELECT p FROM Product p " +
            "WHERE p.category = :category " +
            "AND EXISTS (SELECT 1 FROM ProductDetail pd WHERE pd.product = p AND LOWER(pd.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "AND pd.lang = :lang) " +
            "ORDER BY p.id")
    Page<Product> findByCategoryFilterByName(@Param("category") Category category, @Param("name") String name, @Param("lang") String lang, Pageable pageable);

    @Query("SELECT p FROM Product p " +
            "WHERE EXISTS (SELECT 1 FROM ProductDetail pd WHERE pd.product = p AND LOWER(pd.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "AND pd.lang = :lang) " +
            "ORDER BY p.id")
    Page<Product> findAllFilterByName(@Param("name") String name, @Param("lang") String lang, Pageable pageable);
}