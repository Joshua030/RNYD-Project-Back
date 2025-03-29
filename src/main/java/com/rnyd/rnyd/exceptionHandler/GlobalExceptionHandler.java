package com.rnyd.rnyd.exceptionHandler;

import com.rnyd.rnyd.exceptionHandler.exceptions.RegisterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RegisterException.class)
    public ResponseEntity<ErrorResponse> handleUserEmailAlreadyExists(RegisterException ex, WebRequest webRequest){
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), new Date(), webRequest.getDescription(false));

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
