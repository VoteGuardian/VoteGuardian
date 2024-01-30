package com.blockchain.voteguardian.vote.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "voteblacklist")
public class VoteBlackList {
    @Id
    @NotNull
    @Column(name = "vote_id")
    private Long voteId;

    @OneToOne
    @JoinColumn(name = "vote_id")
    @MapsId
    private Vote vote;

    @NotNull
    @Column(name = "deleted_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Timestamp deletedAt;

    public static VoteBlackList deleteVote(Vote vote, Timestamp now){
        return VoteBlackList.builder()
                .vote(vote)
                .deletedAt(now)
                .build();
    }
}
