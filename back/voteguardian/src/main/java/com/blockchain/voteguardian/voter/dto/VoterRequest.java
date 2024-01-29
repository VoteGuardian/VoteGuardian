package com.blockchain.voteguardian.voter.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

public class VoterRequest {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Create{

        @NotNull
        private String name;

        @Email
        @NotNull
        private String email;
    }
}
