package com.blockchain.voteguardian.global.error.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode{

    EMAIL_ALREADY_EXISTS(1001, HttpStatus.NOT_ACCEPTABLE, "Email already exists"),
    PASSWORDS_DO_NOT_MATCH(1002, HttpStatus.NOT_ACCEPTABLE, "Passwords do not match"),
    CODES_DO_NOT_MATCH(1003, HttpStatus.NOT_ACCEPTABLE, "Codes do not match"),
    USER_DOES_NOT_EXIST(1005,HttpStatus.NOT_ACCEPTABLE, "User does not exist");

    private final int code;
    private final HttpStatus httpStatus;
    private final String message;

}
