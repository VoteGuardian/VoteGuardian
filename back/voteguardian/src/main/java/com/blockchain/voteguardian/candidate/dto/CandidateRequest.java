package com.blockchain.voteguardian.candidate.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.ArrayList;

public class CandidateRequest {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Create{

        @NotNull
        @Min(1)
        private int num;

        @NotNull
        private boolean picture;

        @NotNull
        private String name;

        @NotNull
        private String content;

        private ArrayList<Object> tagList;
    }
}
