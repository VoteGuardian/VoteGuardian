package com.blockchain.voteguardian.vote.dto;

import com.blockchain.voteguardian.vote.entity.Vote;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import java.sql.Timestamp;

@Builder
@Data
public class MainVote {
    private long id;
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "Asia/Seoul")
    private Timestamp startAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "Asia/Seoul")
    private Timestamp finishAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "Asia/Seoul")
    private Timestamp createdAt;
    private int state;

    public static MainVote Create(Vote vote, int state){
        return MainVote.builder()
                .id(vote.getVoteId())
                .title(vote.getTitle())
                .startAt(vote.getStartAt())
                .finishAt(vote.getFinishAt())
                .createdAt(vote.getCreatedAt())
                .state(state)
                .build();
    }
}
