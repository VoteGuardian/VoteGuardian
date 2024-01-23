package com.blockchain.voteguardian.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "userblacklist")
public class UserBlackList {
    @Id
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @MapsId
    private User user;

    @NotNull
    @Column(name = "deleted_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Timestamp deletedAt;

    public static UserBlackList deleteUser(User user, Timestamp now){
        return UserBlackList.builder()
                .user(user)
                .deletedAt(now)
                .build();
    }
}

