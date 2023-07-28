package com.example.resilience4jdemo.exception;

public class RetryException extends RuntimeException {
    public RetryException(String message) {
        super(message);
    }
}
