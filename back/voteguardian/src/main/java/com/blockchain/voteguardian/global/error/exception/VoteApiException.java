package com.blockchain.voteguardian.global.error.exception;

import com.blockchain.voteguardian.global.error.model.ErrorCode;
import lombok.Getter;

@Getter
public class VoteApiException extends RuntimeException{
    private final ErrorCode errorCode;
    public VoteApiException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
