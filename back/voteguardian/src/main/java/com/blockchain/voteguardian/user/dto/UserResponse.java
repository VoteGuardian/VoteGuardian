package com.blockchain.voteguardian.user.dto;

import lombok.*;

public class UserResponse {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class login {
        private String nickname;
        private String email;

        public static UserResponse.login build(String nickname, String email){
            return login.builder()
                    .nickname(nickname)
                    .email(email)
                    .build();
        }
    }
}
