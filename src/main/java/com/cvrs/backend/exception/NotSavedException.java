package com.cvrs.backend.exception;

public class NotSavedException extends RuntimeException{

    public NotSavedException(String message) {
        super(message);
    }

    public NotSavedException(String message, Exception ex) {
        super(message, ex);
    }
}
