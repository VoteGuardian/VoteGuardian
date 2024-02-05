package com.blockchain.voteguardian.votehistory.dto;

import lombok.*;

public class VoteHistoryRequest {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Create{
        private String email;
        private long voteId;
        private long candidateId;
    }


}
