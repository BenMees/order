package com.order.exceptions;

public class ObjectDoesNotExist extends RuntimeException {
    public ObjectDoesNotExist(String message) {
        super(message + " does not exist in our database.");
    }
}
