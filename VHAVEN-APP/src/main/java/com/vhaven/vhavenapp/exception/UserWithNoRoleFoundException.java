package com.vhaven.vhavenapp.exception;

public class UserWithNoRoleFoundException extends RuntimeException {
    public UserWithNoRoleFoundException(String message) {
        super(message);
    }
}
