package com.laberit.sina.bootcamp.modulo3.spring_web.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class EmailNotFoundException extends UsernameNotFoundException {
    public EmailNotFoundException(String message) {
        super(message);
    }
}