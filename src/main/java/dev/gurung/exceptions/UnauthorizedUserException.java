package dev.gurung.exceptions;

public class UnauthorizedUserException extends Exception{
    public UnauthorizedUserException(String message) {
        super(message);
    }
}
