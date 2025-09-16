package com.chatservice.users.exception;

import lombok.Getter;

@Getter
public class PersonalIDNotFoundException extends RuntimeException{

    private final String errorCode;
    private final String errorMessageKey;

    public PersonalIDNotFoundException(ErrorCode code){
        super(code.getErrorMessageKey());
        this.errorCode = code.getErrorCode();
        this.errorMessageKey = code.getErrorMessageKey();
    }

    public PersonalIDNotFoundException(final String message){
        super(message);
        this.errorCode = ErrorCode.CANNOT_FIND_PID.getErrorCode();
        this.errorMessageKey = ErrorCode.CANNOT_FIND_PID.getErrorMessageKey();;
    }
}
