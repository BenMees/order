package com.order.api;

import com.order.exceptions.ObjectAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NullPointerException.class)
    protected void NullPointerException(NullPointerException nullPointerException, HttpServletResponse response) throws IOException {
        logAndSendError(nullPointerException, response, BAD_REQUEST);
    }

    @ExceptionHandler(ObjectAlreadyExistException.class)
    protected void ObjectAlreadyExistException(ObjectAlreadyExistException objectAlreadyExistException, HttpServletResponse response) throws IOException {
        logAndSendError(objectAlreadyExistException, response, BAD_REQUEST);
    }

    private void logAndSendError(Exception emailException, HttpServletResponse response, HttpStatus httpStatus) throws IOException {
        response.sendError(httpStatus.value(), emailException.getMessage());
        logger.error(emailException.getMessage(), emailException);
    }
}
