package com.laberit.sina.bootcamp.modulo3.spring_web.dto;

import com.laberit.sina.bootcamp.modulo3.spring_web.model.Product;

public class ProductDTO {
    private Long id;
    private String code;
    private Double price;
    private String name;
    private String description;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.code = product.getCode();
        this.price = product.getPrice();
        // TODO: this.name =
        // TODO: this.description = description;
    }
}
