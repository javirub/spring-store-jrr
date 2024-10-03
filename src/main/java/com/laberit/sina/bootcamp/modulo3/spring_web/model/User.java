package com.laberit.sina.bootcamp.modulo3.spring_web.model;

import com.laberit.sina.bootcamp.modulo3.spring_web.enumeration.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;
}
