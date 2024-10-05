package com.laberit.sina.bootcamp.modulo3.spring_web.service;

public interface UserService {
    void registerUser(String name, String email, String password);
    void loginUser(String email, String password);
}
