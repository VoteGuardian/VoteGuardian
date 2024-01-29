package com.blockchain.voteguardian.vote.dto;

import com.blockchain.voteguardian.candidate.dto.CandidateRequest;
import com.blockchain.voteguardian.voter.dto.VoterRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

public class VoteRequest {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Create{

        @NotNull
        @Email
        private String email;

        @NotNull
        private String title;

        @NotNull
        private String content;

        @NotNull
        private boolean type;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "Asia/Seoul" )
        @NotNull
        private Timestamp startAt;

        @NotNull
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "Asia/Seoul" )
        private Timestamp finishAt;

        @Valid
        private List<CandidateRequest.Create> candidateList;

        @Valid
        private List<VoterRequest.Create> voterList;
    }
}
