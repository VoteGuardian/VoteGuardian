package com.blockchain.voteguardian.vote.entity;

import com.blockchain.voteguardian.user.entity.User;
import com.blockchain.voteguardian.vote.dto.VoteRequest;
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
@Table(name = "vote")
@ToString
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="vote_id", nullable = false)
    private Long voteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private boolean type;

    @NotNull
    @Column(name = "start_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Timestamp startAt;

    @NotNull
    @Column(name = "finish_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Timestamp finishAt;

    @NotNull
    @Column(name = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Timestamp createdAt;

    @Builder.Default
    @NotNull
    private int percent = 0;

    @Builder.Default
    @NotNull
    private int giveupCount = 0;

    @Builder.Default
    @NotNull
    private int totalCount = 0;

    public static Vote VoteCreate(VoteRequest.Create request, User user, Timestamp now){
        return Vote.builder()
                .user(user)
                .title(request.getTitle())
                .content(request.getContent())
                .startAt(request.getStartAt())
                .finishAt(request.getFinishAt())
                .createdAt(now)
                .type(request.isType())
                .build();
    }

}
