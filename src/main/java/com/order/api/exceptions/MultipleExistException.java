package com.order.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MultipleExistException extends RuntimeException {
    public MultipleExistException(String whatIsDouble) {
        super(whatIsDouble + " already exist");
    }
}
