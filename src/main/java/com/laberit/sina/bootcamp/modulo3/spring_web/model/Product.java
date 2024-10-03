package com.laberit.sina.bootcamp.modulo3.spring_web.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;

    @NotNull
    @PositiveOrZero
    private Double price;

    @NotNull
    @PositiveOrZero
    private Long stock;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProductDetail> productDetail = new ArrayList<>();


    public Product(String code, Double price, Long stock, List<ProductDetail> productDetail) {
        this.code = code;
        this.price = price;
        this.stock = stock;
        this.productDetail = productDetail;
    }

    public Product(String code, Double price, Long stock) {
        this.code = code;
        this.price = price;
        this.stock = stock;
    }

    public Product(Product product){
        this.code = product.getCode();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.productDetail = product.getProductDetail();
    }

    public Product() {
    }
}
