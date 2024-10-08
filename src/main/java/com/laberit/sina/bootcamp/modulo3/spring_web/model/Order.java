package com.laberit.sina.bootcamp.modulo3.spring_web.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "\"order\"")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) // varios pedidos pueden tener el mismo producto
    private Product product;
    @ManyToOne
    private User user;
    private Date date;
    private int quantity;

    public Order() {
    }

    public Order(Product product, User user, Date date, int quantity) {
        this.product = product;
        this.user = user;
        this.date = date;
        this.quantity = quantity;
    }
}
