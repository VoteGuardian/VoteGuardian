package com.blockchain.voteguardian.candidate.dto;

import com.blockchain.voteguardian.candidate.entity.Candidate;
import lombok.*;

import java.util.List;

public class CandidateResponse {
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Detail {
        private long candidateId;
        private int num;
        private String picture;
        private String name;
        private String content;
        private List<Tag> tagList;

        public static CandidateResponse.Detail build(Candidate candidate, List<Tag> tagList){
            return Detail.builder()
                    .candidateId(candidate.getCandidateId())
                    .num(candidate.getNum())
                    .picture(candidate.getPicture())
                    .name(candidate.getName())
                    .content(candidate.getContent())
                    .tagList(tagList)
                    .build();
        }
    }
}
