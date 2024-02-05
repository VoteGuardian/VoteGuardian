package com.blockchain.voteguardian.votehistory.entity;

import com.blockchain.voteguardian.vote.entity.Vote;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "votehistory")
public class VoteHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id", nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vote_id", nullable = false)
    private Vote vote;

    @NotNull
    private long votedId;

    @NotNull
    private String encryptUserId;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Timestamp voteDay;

    public static VoteHistory voteHistoryCreate(Vote vote, long votedId, Timestamp now, String encryptUserId){
        return VoteHistory.builder()
                .vote(vote)
                .votedId(votedId)
                .encryptUserId(encryptUserId)
                .voteDay(now)
                .build();
    }


}
