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
    ALREADY_PARTICIPATED_IN_THE_VOTE(2005, HttpStatus.NOT_ACCEPTABLE, "Already participated in the vote"),
    FAILED_TO_REGISTER_PHOTO(2006, HttpStatus.NOT_ACCEPTABLE, "Failed to register photo"),
    PAGE_DOES_NOT_EXIST(2007, HttpStatus.NOT_ACCEPTABLE, "Page does not exist"),
    DOES_NOT_HAVE_EXACTLY_VALUES(2008, HttpStatus.NOT_ACCEPTABLE, "Does not have exactly values"),
    VOTE_HAS_NOT_STARTED(2009, HttpStatus.NOT_ACCEPTABLE, "Vote has not started"),
    CANDIDATE_DOES_NOT_EXIST(2010, HttpStatus.NOT_ACCEPTABLE, "Candidate does not exist");

    private final int code;
    private final HttpStatus httpStatus;
    private final String message;

}
