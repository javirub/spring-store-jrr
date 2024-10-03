package com.laberit.sina.bootcamp.modulo3.spring_web.service;

import com.laberit.sina.bootcamp.modulo3.spring_web.model.ProductDetail;
import com.laberit.sina.bootcamp.modulo3.spring_web.repository.ProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Override
    public List<ProductDetail> getAllProductDetails() {
        return productDetailRepository.findAll();
    }

    @Override
    public ProductDetail getProductDetailById(Long id) {
        return productDetailRepository.findById(id).orElse(null);
    }

    @Override
    public void addProductDetail(ProductDetail productDetail) {
        productDetailRepository.save(productDetail);
    }

    @Override
    public void updateProductDetail(Long id, ProductDetail productDetail) {
        if (productDetailRepository.existsById(id)) {
            productDetail.setId(id);
            productDetailRepository.save(productDetail);
        }
    }

    @Override
    public void deleteProductDetail(Long id) {
        productDetailRepository.deleteById(id);
    }
}