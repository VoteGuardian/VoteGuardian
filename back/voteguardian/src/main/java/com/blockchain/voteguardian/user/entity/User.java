package com.blockchain.voteguardian.user.entity;

import com.blockchain.voteguardian.user.dto.UserRequest;
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
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id", nullable = false)
    private Long userId;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String nickname;

    @NotNull
    @Column(name = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Timestamp createdAt;

    @NotNull
    @Column(name = "wallet_address")
    private String walletAddress;

    @NotNull
    @Column(name = "wallet_private_key")
    private String walletPrivateKey;

    public static User UserCreate(UserRequest.Create request, Timestamp now){
        return User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .nickname(request.getNickname())
                .createdAt(now)
                .build();
    }

    public void WalletCreate(String walletAddress, String walletPrivateKey){
        this.walletAddress = walletAddress;
        this.walletPrivateKey = walletPrivateKey;
    }

}
