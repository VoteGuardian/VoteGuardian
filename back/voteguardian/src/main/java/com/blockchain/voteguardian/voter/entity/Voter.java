package com.blockchain.voteguardian.voter.entity;

import com.blockchain.voteguardian.vote.entity.Vote;
import com.blockchain.voteguardian.voter.dto.VoterRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Voter")
public class Voter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="voter_id", nullable = false)
    private Long voterId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vote_id", nullable = false)
    private Vote vote;

    @NotNull
    private String name;

    @NotNull
    private String email;

    public static Voter VoterCreate(VoterRequest.Create request, Vote vote){
        return Voter.builder()
                .vote(vote)
                .name(request.getName())
                .email(request.getEmail())
                .build();
    }


}
