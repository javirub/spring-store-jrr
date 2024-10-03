package com.laberit.sina.bootcamp.modulo3.spring_web.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "product_detail")
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String lang;

    @ManyToOne(fetch= FetchType.LAZY, optional = false)
    @JoinColumn(name="product_id")
    @JsonBackReference
    private Product product;

    public ProductDetail(){}

    public ProductDetail(String name, String description, String lang, Product product) {
        this.name = name;
        this.description = description;
        this.lang = lang;
        this.product = product;
    }
}
