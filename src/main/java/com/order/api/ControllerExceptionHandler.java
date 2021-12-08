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
        logAndSendError(nullPointerException, response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ObjectAlreadyExistException.class)
    protected void setObjectAlreadyExistException(ObjectAlreadyExistException objectAlreadyExistException, HttpServletResponse response) throws IOException {
        logAndSendError(objectAlreadyExistException, response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ObjectDoesNotExist.class)
    protected void setObjectDoesNotExist(ObjectDoesNotExist objectDoesNotExist, HttpServletResponse response) throws IOException {
        logAndSendError(objectDoesNotExist, response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    protected void setUnauthorizedException(UnauthorizedException unauthorizedException, HttpServletResponse response) throws IOException {
        logAndSendError(unauthorizedException, response, HttpStatus.UNAUTHORIZED);
    }

    private void logAndSendError(Exception emailException, HttpServletResponse response, HttpStatus ErrorCode) throws IOException {
        response.sendError(ErrorCode.value(), emailException.getMessage());
        logger.error(emailException.getMessage(), emailException);
    }
}
