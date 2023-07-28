package com.example.resilience4jdemo.exception;

public class SomeException extends RuntimeException {
    public SomeException(String message) {
        super(message);
    }
}
