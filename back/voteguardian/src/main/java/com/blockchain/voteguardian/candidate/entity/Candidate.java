package com.blockchain.voteguardian.candidate.entity;

import com.blockchain.voteguardian.candidate.dto.CandidateRequest;
import com.blockchain.voteguardian.vote.entity.Vote;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "candidate")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="candidate_id", nullable = false)
    private Long candidateId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vote_id", nullable = false)
    private Vote vote;

    private String picture;

    @NotNull
    private String name;


    private String tag;

    @NotNull
    private String content;

    @NotNull
    private int num;

    @Builder.Default
    @NotNull
    private int count = 0;

    public static Candidate candidateCreate(CandidateRequest.Create request, Vote vote, String photo, String tag){
        return Candidate.builder()
                .vote(vote)
                .picture(photo)
                .name(request.getName())
                .tag(tag)
                .content(request.getContent())
                .num(request.getNum())
                .build();
    }

}
