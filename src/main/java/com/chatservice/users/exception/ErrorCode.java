package com.chatservice.users.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    CANNOT_CREATE_RESOURCE("001", "Cannot Create The Request Object."),
    CANNOT_FIND_PID("002", "Cannot Validate Personal Id.");


    private final String errorCode;
    private final String errorMessageKey;

    ErrorCode(String errorCode, String errorMessageKey){
        this.errorCode = errorCode;
        this.errorMessageKey = errorMessageKey;
    }


}
