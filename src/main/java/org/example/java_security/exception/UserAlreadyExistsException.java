package org.example.java_security.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException() {
        super("User already exist");
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}