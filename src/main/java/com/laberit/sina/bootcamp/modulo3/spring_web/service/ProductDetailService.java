package com.laberit.sina.bootcamp.modulo3.spring_web.service;

import com.laberit.sina.bootcamp.modulo3.spring_web.model.ProductDetail;

import java.util.List;

public interface ProductDetailService {
    List<ProductDetail> getAllProductDetails();
    ProductDetail getProductDetailById(Long id);
    void addProductDetail(ProductDetail productDetail);
    void updateProductDetail(Long id, ProductDetail productDetail);
    void deleteProductDetail(Long id);
}