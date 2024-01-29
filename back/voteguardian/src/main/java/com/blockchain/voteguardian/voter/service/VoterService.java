package com.blockchain.voteguardian.voter.service;

import com.blockchain.voteguardian.vote.entity.Vote;
import com.blockchain.voteguardian.voter.dto.VoterRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VoterService {
    void create(List<VoterRequest.Create> voterList,  Vote vote);
}
