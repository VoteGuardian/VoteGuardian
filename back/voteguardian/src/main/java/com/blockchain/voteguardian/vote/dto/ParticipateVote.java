package com.blockchain.voteguardian.vote.dto;

import com.blockchain.voteguardian.vote.entity.Vote;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
public class ParticipateVote {
    private long id;
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "Asia/Seoul")
    private Timestamp startAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "Asia/Seoul")
    private Timestamp finishAt;
    private boolean voted;

    public static ParticipateVote Create(Vote vote, boolean voted){
        return ParticipateVote.builder()
                .id(vote.getVoteId())
                .title(vote.getTitle())
                .startAt(vote.getStartAt())
                .finishAt(vote.getFinishAt())
                .voted(voted)
                .build();
    }
}
