package com.laberit.sina.bootcamp.modulo3.spring_web.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "product_statistic")
public class ProductStatistic {
    @Id
    private Long id;
    private Long count;
    @OneToOne
    @MapsId
    private Product product;
}
