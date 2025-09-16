package com.chatservice.users.exception;

public class ErrorUtil {

    private ErrorUtil(){};

    public Error create(String code, String message, Integer httpStatusCode) {
        return new Error(code, message, httpStatusCode);
    }
}
