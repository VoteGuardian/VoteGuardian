package com.blockchain.voteguardian.vote.service;

import com.blockchain.voteguardian.candidate.service.CandidateService;
import com.blockchain.voteguardian.global.properties.ConstProperties;
import com.blockchain.voteguardian.user.entity.User;
import com.blockchain.voteguardian.user.repository.UserBlackListRepository;
import com.blockchain.voteguardian.user.repository.UserRepository;
import com.blockchain.voteguardian.vote.dto.MainVote;
import com.blockchain.voteguardian.vote.dto.VoteRequest;
import com.blockchain.voteguardian.vote.dto.VoteResponse;
import com.blockchain.voteguardian.vote.entity.Vote;
import com.blockchain.voteguardian.vote.repository.VoteRepository;
import com.blockchain.voteguardian.vote.repository.VoteRepositoryImpl;
import com.blockchain.voteguardian.voter.service.VoterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService{

    private final VoterService voterService;
    private final CandidateService candidateService;
    private final UserRepository userRepository;
    private final VoteRepository voteRepository;
    private final UserBlackListRepository userBlackListRepository;
    private final ConstProperties constProperties;
    private final VoteRepositoryImpl voteRepositoryImpl;
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

    @Override
    public VoteResponse.MainVoteList mainVoteList(int state, int page, String email) {
        User user = userRepository.findTop1ByEmailOrderByUserIdDesc(email);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Pageable pageable = PageRequest.of(page, constProperties.getSize());
        List<Vote> list;
        int totalCnt = 0;
        if(state == 0){ // 전체 투표 조회
            list = voteRepositoryImpl.mainTotalList(user, pageable);
            totalCnt = voteRepositoryImpl.mainTotalVoteCnt(user);
        }else if(state == 1){
            list = voteRepositoryImpl.mainPreList(user, pageable, now);
            totalCnt = voteRepositoryImpl.mainPreVoteCnt(user, now);
        }else if(state == 2){
            list = voteRepositoryImpl.mainProgressList(user, pageable, now);
            totalCnt = voteRepositoryImpl.mainProgressVoteCnt(user, now);
        }else{
            list = voteRepositoryImpl.mainEndList(user, pageable, now);
            totalCnt = voteRepositoryImpl.mainEndVoteCnt(user, now);
        }
        int totalPageCnt;
        if(totalCnt == 0){
            totalPageCnt = 0;
        }else{
            totalPageCnt = totalCnt/4;
            if(totalCnt % 4 != 0){
                totalPageCnt++;
            }
        }
        List<MainVote> mainVoteList;
        if(state == 0){
            mainVoteList = checkStateAndMakeVote(list);
        }else{
            mainVoteList = changeMainVote(list, state);
        }

        return VoteResponse.MainVoteList.build(totalPageCnt, mainVoteList);
    }

    private List<MainVote> checkStateAndMakeVote(List<Vote> list) {
        List<MainVote> mainVoteList = new ArrayList<>();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        for(Vote v : list){
            int state = 0;
            if(v.getStartAt().after(now)){
                state = 1;
            }else if(v.getFinishAt().before(now) || v.getFinishAt().equals(now)){
                state = 3;
            }else{
                state = 2;
            }
            MainVote mv = MainVote.Create(v, state);
            mainVoteList.add(mv);
        }
        return mainVoteList;
    }

    private List<MainVote> changeMainVote(List<Vote> list, int state) {
        List<MainVote> mainVoteList = new ArrayList<>();
        for(Vote v : list){
            MainVote mv = MainVote.Create(v, state);
            mainVoteList.add(mv);
        }
        return mainVoteList;
    }
}
