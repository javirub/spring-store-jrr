package com.laberit.sina.bootcamp.modulo3.spring_web.service;


import com.laberit.sina.bootcamp.modulo3.spring_web.model.Product;
import com.laberit.sina.bootcamp.modulo3.spring_web.model.User;

public interface OrderService {
    void createOrder(Product product, User user, Integer quantity) throws Exception;
}
