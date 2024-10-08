package com.laberit.sina.bootcamp.modulo3.spring_web.service;

import com.laberit.sina.bootcamp.modulo3.spring_web.model.Order;
import com.laberit.sina.bootcamp.modulo3.spring_web.model.Product;
import com.laberit.sina.bootcamp.modulo3.spring_web.model.User;
import com.laberit.sina.bootcamp.modulo3.spring_web.repository.OrderRepository;
import com.laberit.sina.bootcamp.modulo3.spring_web.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void createOrder(Product product, User user, Integer quantity) throws RuntimeException {
        if (product.getStock() < quantity) {
            throw new CannotCreateTransactionException("Not enough stock");
        }
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);

        Order order = new Order(product, user, new Date(), quantity);
        orderRepository.save(order);
    }
}
