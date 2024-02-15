package com.blockchain.voteguardian.vote.dto;

import com.blockchain.voteguardian.candidate.dto.CandidateResponse;
import com.blockchain.voteguardian.vote.entity.Vote;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.sql.Timestamp;
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

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ParticipateVoteList {
        private int totalPageCnt;
        private List<ParticipateVote> voteList;

        public static VoteResponse.ParticipateVoteList build(int totalPageCnt, List<ParticipateVote> voteList){
            return ParticipateVoteList.builder()
                    .totalPageCnt(totalPageCnt)
                    .voteList(voteList)
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class LinkVoteDetail {
        private String title;
        private String content;
        private boolean type;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "Asia/Seoul")
        private Timestamp startAt;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "Asia/Seoul")
        private Timestamp finishAt;
        private List<CandidateResponse.Detail> candidateList;

        public static VoteResponse.LinkVoteDetail build(Vote vote, List<CandidateResponse.Detail> candidateList){
            return LinkVoteDetail.builder()
                    .title(vote.getTitle())
                    .content(vote.getContent())
                    .type(vote.isType())
                    .startAt(vote.getStartAt())
                    .finishAt(vote.getFinishAt())
                    .candidateList(candidateList)
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Result {
        private String title;
        private String content;
        private boolean type;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "Asia/Seoul")
        private Timestamp startAt;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "Asia/Seoul")
        private Timestamp finishAt;
        private List<CandidateResponse.Result> candidateList;

        public static VoteResponse.Result build(Vote vote, List<CandidateResponse.Result> candidateList){
            return Result.builder()
                    .title(vote.getTitle())
                    .content(vote.getContent())
                    .type(vote.isType())
                    .startAt(vote.getStartAt())
                    .finishAt(vote.getFinishAt())
                    .candidateList(candidateList)
                    .build();
        }
    }
}
