package com.chatservice.users.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(CannotCreateResourceException.class)
    public ResponseEntity<Map<String, Object>> handleCannotCreate(CannotCreateResourceException ex) {
        // Optional logging for business exceptions
        logger.warn("Resource creation failed: code={}, message={}", ex.getErrorCode(), ex.getErrorMessageKey());

        Map<String, Object> body = new HashMap<>();
        body.put("errorCode", ex.getErrorCode());
        body.put("message", ex.getErrorMessageKey());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String ,Object>> handlePersonalIdNotFound(PersonalIDNotFoundException ex){
        logger.warn("code={}, message={}", ex.getErrorCode(), ex.getErrorMessageKey());

        Map<String, Object> body = new HashMap<>();
        body.put("errorCode", ex.getErrorCode());
        body.put("message", ex.getErrorMessageKey());

        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handleUnexpected(Exception e){
        logger.error("Unexpected Error", e);
        Map<String, Object> body = new HashMap<>();
        body.put("errorCode", "INTERNAL_ERROR");
        body.put("errorMessageKey", "Something Went Wrong");

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
