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
    @OneToOne(fetch = FetchType.LAZY, optional = false) // un pedido para cada producto, puede cambiarse a OneToMany
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
