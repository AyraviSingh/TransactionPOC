package com.idempotency.exception;

/**
 * This is the exception method,
 * to handle Resource not found exception.
 */
public class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
