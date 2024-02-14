package com.blockchain.voteguardian.vote.service;

import com.blockchain.voteguardian.vote.dto.VoteRequest;
import com.blockchain.voteguardian.vote.dto.VoteResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface VoteService {
    VoteResponse.CreateVote create(VoteRequest.Create request, List<MultipartFile> photos) throws JsonProcessingException;

    VoteResponse.MainVoteList mainVoteList(int state, int page, String email);

    VoteResponse.ParticipateVoteList participateVoteList(int state, int page, String email) throws Exception;
}
