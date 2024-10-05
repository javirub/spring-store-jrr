package com.laberit.sina.bootcamp.modulo3.spring_web.repository;

import com.laberit.sina.bootcamp.modulo3.spring_web.exception.EmailNotFoundException;
import com.laberit.sina.bootcamp.modulo3.spring_web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    default User findByEmailOrThrow(String email) {
        return findByEmail(email).orElseThrow(() -> new EmailNotFoundException("Email not found: " + email));
    }

    Optional<User> findByEmailAndPassword(String email, String password);

    default User findByEmailAndPasswordOrThrow(String email, String password) {
        return findByEmailAndPassword(email, password).orElseThrow(() -> new EmailNotFoundException("No user found with provided email and password"));
    }
}