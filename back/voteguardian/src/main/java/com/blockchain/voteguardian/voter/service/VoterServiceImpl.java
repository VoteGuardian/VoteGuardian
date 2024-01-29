package com.blockchain.voteguardian.voter.service;

import com.blockchain.voteguardian.vote.entity.Vote;
import com.blockchain.voteguardian.voter.dto.VoterRequest;
import com.blockchain.voteguardian.voter.entity.Voter;
import com.blockchain.voteguardian.voter.repository.VoterRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VoterServiceImpl implements VoterService {

    private final VoterRepository voterRepository;
    @Override
    public void create(@Validated List<VoterRequest.Create> voterList, Vote vote) {
        for(VoterRequest.Create req : voterList){
            Voter voter = Voter.VoterCreate(req, vote);
            voterRepository.save(voter);
        }
    }
}
