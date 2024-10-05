package com.laberit.sina.bootcamp.modulo3.spring_web.service;

import com.laberit.sina.bootcamp.modulo3.spring_web.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public void registerUser(String name, String email, String password) {
        if(userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        entityManager.persist(name);
    }

    @Override
    public void loginUser(String email, String password) {
        userRepository.findByEmailAndPasswordOrThrow(email, password);
    }
}
