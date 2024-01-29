package com.blockchain.voteguardian.global.error.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum VoteErrorCode implements ErrorCode{

    VOTE_DOES_NOT_HAVE_EXACTLY_VALUES(2001, HttpStatus.NOT_ACCEPTABLE, "Vote does not have exactly values"),
    VOTE_DOES_NOT_EXIST(2002, HttpStatus.NOT_ACCEPTABLE, "Vote does not exist"),
    VOTE_HAS_BEEN_ENDED(2003, HttpStatus.NOT_ACCEPTABLE, "Vote has been ended"),
    VOTE_HAS_BEEN_DELETED(2004,HttpStatus.NOT_ACCEPTABLE, "Vote has been deleted"),
    ALREADY_PARTICIPATED_IN_THE_VOTE(2005, HttpStatus.NOT_ACCEPTABLE, "Already participated in the vote");

    private final int code;
    private final HttpStatus httpStatus;
    private final String message;

}
