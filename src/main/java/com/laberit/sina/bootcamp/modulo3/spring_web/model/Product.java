package com.laberit.sina.bootcamp.modulo3.spring_web.model;

public class Product {
    private Long id;
    private String code;
    private Double price;
    private Long stock;
    private ProductDetail[] productDetail;

    public Product(Long id, String code, Double price, Long stock, ProductDetail[] productDetail) {
        this.id = id;
        this.code = code;
        this.price = price;
        this.stock = stock;
        this.productDetail = productDetail;
    }

    public Product(String code, Double price, Long stock, ProductDetail[] productDetail) {
        this.id = null;
        this.code = code;
        this.price = price;
        this.stock = stock;
        this.productDetail = productDetail;
    }

    public Product(String code, Double price, Long stock) {
        this.id = null;
        this.code = code;
        this.price = price;
        this.stock = stock;
        this.productDetail = null;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public ProductDetail[] getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetail[] productDetail) {
        this.productDetail = productDetail;
    }
}
