package com.blockchain.voteguardian.user.dto;

import lombok.*;

public class UserRequest {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Create{
        private String email;
        private String nickname;
        private String password;
    }
}
