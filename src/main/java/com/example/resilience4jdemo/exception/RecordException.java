package com.example.resilience4jdemo.exception;

public class RecordException extends RuntimeException {
    public RecordException(String message) {
        super(message);
    }
};
