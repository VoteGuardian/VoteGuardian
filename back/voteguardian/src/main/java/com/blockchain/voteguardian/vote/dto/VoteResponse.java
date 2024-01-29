package com.blockchain.voteguardian.vote.dto;

import lombok.*;

public class VoteResponse {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CreateVote {
        private long voteId;

        public static VoteResponse.CreateVote build(long voteId){
            return CreateVote.builder()
                    .voteId(voteId)
                    .build();
        }
    }
}
