package com.blockchain.voteguardian.user.dto;

import lombok.*;

public class UserResponse {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class AuthResponse{

        private String key;

        public static UserResponse.AuthResponse build(String key){
            return UserResponse.AuthResponse.builder()
                    .key(key)
                    .build();
        }
    }
}
