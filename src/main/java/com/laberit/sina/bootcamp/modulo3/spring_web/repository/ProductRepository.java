package com.laberit.sina.bootcamp.modulo3.spring_web.repository;

import com.laberit.sina.bootcamp.modulo3.spring_web.enumeration.Category;
import com.laberit.sina.bootcamp.modulo3.spring_web.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);

    @Query("SELECT p FROM Product p JOIN FETCH p.productDetail WHERE p.category = :category")
    List<Product> findByCategoryWithDetail(Category category);

    @Query("SELECT p FROM Product p JOIN FETCH p.productDetail")
    List<Product> findAllWithDetail();

    @Query("SELECT p FROM Product p JOIN FETCH p.productDetail d WHERE p.category = :category AND d.lang = :lang AND LOWER(d.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Product> findByCategoryWithDetailFilterByName(@Param("category") Category category, @Param("name") String name, @Param("lang") String lang);

    @Query("SELECT p FROM Product p JOIN FETCH p.productDetail d WHERE d.lang = :lang AND LOWER(d.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Product> findAllWithDetailFilterByName(@Param("name") String name, @Param("lang") String lang);
}