package com.laberit.sina.bootcamp.modulo3.spring_web.exception;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException(String message) {
        super(message);
    }
}