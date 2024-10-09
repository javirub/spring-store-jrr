package com.laberit.sina.bootcamp.modulo3.spring_web.repository;

import com.laberit.sina.bootcamp.modulo3.spring_web.model.ProductStatistic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductStatisticRepository extends JpaRepository<ProductStatistic, Long> {
}
