package com.order.api;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NullPointerException.class)
    protected void NullPointerException(NullPointerException emailException, HttpServletResponse response) throws IOException {
        response.sendError(BAD_REQUEST.value(), emailException.getMessage());
        logger.error(emailException.getMessage(), emailException);
    }
}
