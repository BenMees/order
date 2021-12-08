package com.order.exceptions;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(String email, String feature) {
        super(email + " does not have access to " + feature);
    }
}
