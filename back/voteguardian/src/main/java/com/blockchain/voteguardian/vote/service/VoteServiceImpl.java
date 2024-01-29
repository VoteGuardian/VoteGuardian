package com.blockchain.voteguardian.vote.service;

import com.blockchain.voteguardian.candidate.service.CandidateService;
import com.blockchain.voteguardian.user.entity.User;
import com.blockchain.voteguardian.user.repository.UserRepository;
import com.blockchain.voteguardian.vote.dto.VoteRequest;
import com.blockchain.voteguardian.vote.dto.VoteResponse;
import com.blockchain.voteguardian.vote.entity.Vote;
import com.blockchain.voteguardian.vote.repository.VoteRepository;
import com.blockchain.voteguardian.voter.service.VoterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService{

    private final VoterService voterService;
    private final CandidateService candidateService;
    private final UserRepository userRepository;
    private final VoteRepository voteRepository;
    @Override
    public VoteResponse.CreateVote create(VoteRequest.Create request, List<MultipartFile> photos) throws JsonProcessingException {
        User user = userRepository.findTop1ByEmailOrderByUserIdDesc(request.getEmail());
        Timestamp now = new Timestamp(System.currentTimeMillis());
        // 투표 생성
        Vote vote = Vote.VoteCreate(request, user, now);
        long voteId = voteRepository.save(vote).getVoteId();

        // 투표자 생성
        voterService.create(request.getVoterList(), vote);
        // 후보자 생성
        candidateService.create(request.getCandidateList(), photos, vote);
        return VoteResponse.CreateVote.build(voteId);
    }
}
