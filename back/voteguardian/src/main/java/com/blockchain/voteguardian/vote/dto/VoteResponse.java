package com.blockchain.voteguardian.vote.dto;

import lombok.*;
import java.util.List;

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

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class MainVoteList {
        private int totalPageCnt;
        private List<MainVote> voteList;

        public static VoteResponse.MainVoteList build(int totalPageCnt, List<MainVote> voteList){
            return MainVoteList.builder()
                    .totalPageCnt(totalPageCnt)
                    .voteList(voteList)
                    .build();
        }
    }
}
