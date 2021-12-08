package com.order.api;

import com.order.exceptions.ObjectAlreadyExistException;
import com.order.exceptions.ObjectDoesNotExist;
import com.order.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NullPointerException.class)
    protected void setNullPointerException(NullPointerException nullPointerException, HttpServletResponse response) throws IOException {
        logAndSendError(nullPointerException, response);
    }

    @ExceptionHandler(ObjectAlreadyExistException.class)
    protected void setObjectAlreadyExistException(ObjectAlreadyExistException objectAlreadyExistException, HttpServletResponse response) throws IOException {
        logAndSendError(objectAlreadyExistException, response);
    }

    @ExceptionHandler(ObjectDoesNotExist.class)
    protected void setObjectDoesNotExist(ObjectDoesNotExist objectDoesNotExist, HttpServletResponse response) throws IOException {
        logAndSendError(objectDoesNotExist, response);
    }

    @ExceptionHandler(UnauthorizedException.class)
    protected void setUnauthorizedException(UnauthorizedException unauthorizedException, HttpServletResponse response) throws IOException {
        logAndSendError(unauthorizedException, response);
    }

    private void logAndSendError(Exception emailException, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), emailException.getMessage());
        logger.error(emailException.getMessage(), emailException);
    }
}
