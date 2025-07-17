package com.vhaven.vhavenapp.exception;

public class UserWithEmailExistsException extends RuntimeException {
    public UserWithEmailExistsException(String message) {
        super(message);
    }
}
