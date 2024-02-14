package com.blockchain.voteguardian.votehistory.service;

import com.blockchain.voteguardian.candidate.entity.Candidate;
import com.blockchain.voteguardian.candidate.repository.CandidateRepository;
import com.blockchain.voteguardian.global.error.exception.VoteApiException;
import com.blockchain.voteguardian.global.error.model.UserErrorCode;
import com.blockchain.voteguardian.global.error.model.VoteErrorCode;
import com.blockchain.voteguardian.global.properties.EncrptProperties;
import com.blockchain.voteguardian.user.entity.User;
import com.blockchain.voteguardian.user.repository.UserRepository;
import com.blockchain.voteguardian.util.AesUtil;
import com.blockchain.voteguardian.vote.entity.Vote;
import com.blockchain.voteguardian.vote.repository.VoteRepository;
import com.blockchain.voteguardian.votehistory.dto.VoteHistoryRequest;
import com.blockchain.voteguardian.votehistory.entity.VoteHistory;
import com.blockchain.voteguardian.votehistory.repository.VoteHistoryRepository;
import com.blockchain.voteguardian.voter.entity.Voter;
import com.blockchain.voteguardian.voter.repository.VoterRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class VoteHistoryServiceImpl implements VoteHistoryService {

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final VoteHistoryRepository voteHistoryRepository;
    private final CandidateRepository candidateRepository;
    private final VoterRepository voterRepository;
    private final EncrptProperties encrptProperties;

    @Override
    public void participateVote(VoteHistoryRequest.Create request) throws Exception {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Optional<Vote> vote = voteRepository.findById(request.getVoteId());
        User user = userRepository.findTop1ByEmailOrderByUserIdDesc(request.getEmail());
        if(!vote.isPresent()){
            throw new VoteApiException(VoteErrorCode.VOTE_DOES_NOT_EXIST);
        }
        if(user == null){
            throw new VoteApiException(UserErrorCode.USER_DOES_NOT_EXIST);
        }
        if(vote.get().getStartAt().after(now)){
            throw new VoteApiException(VoteErrorCode.VOTE_HAS_NOT_STARTED);
        }
        if(vote.get().getFinishAt().equals(now) || vote.get().getFinishAt().before(now)){
            throw new VoteApiException(VoteErrorCode.VOTE_HAS_BEEN_ENDED);
        }

        // 투표의 투표자 목록에 해당 이메일이 존재하지 않을 때
        Voter voter = voterRepository.findByVote_VoteIdAndEmail(vote.get().getVoteId(), request.getEmail());
        if(voter == null){
            throw new VoteApiException(VoteErrorCode.DOES_NOT_HAVE_EXACTLY_VALUES);
        }

        AesUtil aesUtil = new AesUtil(encrptProperties.getKey());
        String encryptEmail = aesUtil.AesCBCEncode(request.getEmail());

        // 이미 투표에 참여한 기록이 존재할 때
        VoteHistory  already = voteHistoryRepository.findByEncryptEmailAndVote_VoteId(encryptEmail, vote.get().getVoteId());
        if(already != null){
            throw new VoteApiException(VoteErrorCode.ALREADY_PARTICIPATED_IN_THE_VOTE);
        }

        VoteHistory voteHistory;
        // 찬반 투표일 때
        if(vote.get().isType()){
            if(request.getCandidateId() > 1 || request.getCandidateId() < -1){
                throw new VoteApiException(VoteErrorCode.DOES_NOT_HAVE_EXACTLY_VALUES);
            }
            voteHistory = VoteHistory.voteHistoryCreate(vote.get(), request.getCandidateId(), now, encryptEmail);
        }else{ // 선거 투표일 때
            if(request.getCandidateId() != -1){
                Candidate candidate = candidateRepository.findByCandidateIdAndVote_VoteId(request.getCandidateId(), vote.get().getVoteId());
                if(candidate == null){
                    throw new VoteApiException(VoteErrorCode.CANDIDATE_DOES_NOT_EXIST);
                }
            }
            voteHistory = VoteHistory.voteHistoryCreate(vote.get(), request.getCandidateId(), now, encryptEmail);

        }

        voteHistoryRepository.save(voteHistory);

    }
}
