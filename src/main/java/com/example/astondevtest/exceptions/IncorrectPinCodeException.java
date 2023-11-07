package com.example.astondevtest.exceptions;

public class IncorrectPinCodeException extends RuntimeException {
    public IncorrectPinCodeException(String message) {
        super(message);
    }
}
