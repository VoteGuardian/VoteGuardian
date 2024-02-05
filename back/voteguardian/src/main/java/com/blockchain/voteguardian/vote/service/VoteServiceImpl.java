package com.blockchain.voteguardian.vote.service;

import com.blockchain.voteguardian.candidate.service.CandidateService;
import com.blockchain.voteguardian.global.error.exception.VoteApiException;
import com.blockchain.voteguardian.global.error.model.UserErrorCode;
import com.blockchain.voteguardian.global.error.model.VoteErrorCode;
import com.blockchain.voteguardian.global.properties.ConstProperties;
import com.blockchain.voteguardian.user.entity.User;
import com.blockchain.voteguardian.user.repository.UserRepository;
import com.blockchain.voteguardian.vote.dto.MainVote;
import com.blockchain.voteguardian.vote.dto.ParticipateVote;
import com.blockchain.voteguardian.vote.dto.VoteRequest;
import com.blockchain.voteguardian.vote.dto.VoteResponse;
import com.blockchain.voteguardian.vote.entity.Vote;
import com.blockchain.voteguardian.vote.repository.VoteRepository;
import com.blockchain.voteguardian.vote.repository.VoteRepositoryImpl;
import com.blockchain.voteguardian.votehistory.entity.VoteHistory;
import com.blockchain.voteguardian.votehistory.repository.VoteHistoryRepository;
import com.blockchain.voteguardian.voter.repository.VoterRepositoryImpl;
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
    private final ConstProperties constProperties;
    private final VoteRepositoryImpl voteRepositoryImpl;
    private final VoterRepositoryImpl voterRepositoryImpl;
    private final VoteHistoryRepository voteHistoryRepository;

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
        if(page < 0 || state < 0 || state > 3){
            throw new VoteApiException(VoteErrorCode.PAGE_DOES_NOT_EXIST);
        }
        User user = userRepository.findTop1ByEmailOrderByUserIdDesc(email);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Pageable pageable = PageRequest.of(page, constProperties.getSize());
        List<Vote> list;
        int totalCnt;
        int totalPageCnt;
        if(state == 0){ // 전체 투표 조회
            totalCnt = voteRepositoryImpl.mainTotalVoteCnt(user);
            totalPageCnt = changePageCnt(totalCnt);
            if(page == 0 && totalCnt == 0){
                list = null;
            }else{
                pageError(totalPageCnt <= page);
                list = voteRepositoryImpl.mainTotalList(user, pageable);
            }
        }else if(state == 1){ // 예정 투표 조회
            totalCnt = voteRepositoryImpl.mainPreVoteCnt(user, now);
            totalPageCnt = changePageCnt(totalCnt);
            if(page == 0 && totalCnt == 0){
                list = null;
            }else{
                pageError(totalPageCnt <= page);
                list = voteRepositoryImpl.mainPreList(user, pageable, now);
            }
        }else if(state == 2){ // 진행중인 투표 조회
            totalCnt = voteRepositoryImpl.mainProgressVoteCnt(user, now);
            totalPageCnt = changePageCnt(totalCnt);
            if(page == 0 && totalCnt == 0){
                list = null;
            }else{
                pageError(totalPageCnt <= page);
                list = voteRepositoryImpl.mainProgressList(user, pageable, now);
            }
        }else{ // 종료된 투표 조회
            totalCnt = voteRepositoryImpl.mainEndVoteCnt(user, now);
            totalPageCnt = changePageCnt(totalCnt);
            if(page == 0 && totalCnt == 0){
                list = null;
            }else{
                pageError(totalPageCnt <= page);
                list = voteRepositoryImpl.mainEndList(user, pageable, now);
            }
        }
        List<MainVote> mainVoteList;
        if(list == null){
            mainVoteList = null;
        }else{
            if(state == 0){
                mainVoteList = checkStateAndMakeVote(list);
            }else{
                mainVoteList = changeMainVote(list, state);
            }
        }

        return VoteResponse.MainVoteList.build(totalPageCnt, mainVoteList);
    }

    @Override
    public VoteResponse.ParticipateVoteList participateVoteList(int state, int page, String email) {
        if(page < 0 || state < 1 || state > 3){
            throw new VoteApiException(VoteErrorCode.PAGE_DOES_NOT_EXIST);
        }
        User user = userRepository.findTop1ByEmailOrderByUserIdDesc(email);
        if(user == null){
            throw new VoteApiException(UserErrorCode.USER_DOES_NOT_EXIST);
        }
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Pageable pageable = PageRequest.of(page, constProperties.getSize());
        List<Vote> list;
        int totalCnt;
        int totalPageCnt;
        if(state == 1){ // 예정 투표
            totalCnt = voterRepositoryImpl.PreVoteCnt(email, now);
            totalPageCnt = changePageCnt(totalCnt);
            if(page == 0 && totalCnt == 0){
                list = null;
            }else{
                pageError(totalPageCnt <= page);
                list = voterRepositoryImpl.PreList(email, pageable, now);
            }
        }else if(state == 2) { // 진행 중 투표
            totalCnt = voterRepositoryImpl.ProgressVoteCnt(email, now);
            totalPageCnt = changePageCnt(totalCnt);
            if(page == 0 && totalCnt == 0){
                list = null;
            }else{
                pageError(totalPageCnt <= page);
                list = voterRepositoryImpl.ProgressList(email, pageable, now);
            }
        }else{ // 종료 된 투표
            totalCnt = voterRepositoryImpl.EndVoteCnt(email, now);
            totalPageCnt = changePageCnt(totalCnt);
            if(page == 0 && totalCnt == 0){
                list = null;
            }else{
                pageError(totalPageCnt <= page);
                list = voterRepositoryImpl.EndList(email, pageable, now);
            }
        }
        List<ParticipateVote> participateVoteList;
        if(list == null){
            participateVoteList = null;
        }else{
            participateVoteList = changeParticipateVote(list, user);
        }

        return VoteResponse.ParticipateVoteList.build(totalPageCnt, participateVoteList);
    }

    private List<ParticipateVote> changeParticipateVote(List<Vote> list, User user) {
        List<ParticipateVote> res = new ArrayList<>();
        for(Vote v : list){
            /**
             * 암호화 작업 추가로 필요합니다.
             */
            String encryptUserId = user.getUserId().toString();
            boolean participate = false;
            VoteHistory voteHistory = voteHistoryRepository.findByEncryptUserIdAndVote_VoteId(encryptUserId, v.getVoteId());
            if(voteHistory != null){
                participate = true;
            }
            ParticipateVote pv = ParticipateVote.Create(v, participate);
            res.add(pv);
        }
        return res;
    }

    private static void pageError(boolean totalPageCnt) {
        if (totalPageCnt) {
            throw new VoteApiException(VoteErrorCode.PAGE_DOES_NOT_EXIST);
        }
    }

    private int changePageCnt(int totalCnt) {
        int totalPageCnt;
        if(totalCnt == 0){
            totalPageCnt = 0;
        }else{
            totalPageCnt = totalCnt/4;
            if(totalCnt % 4 != 0){
                totalPageCnt++;
            }
        }
        return totalPageCnt;
    }

    private List<MainVote> checkStateAndMakeVote(List<Vote> list) {
        List<MainVote> mainVoteList = new ArrayList<>();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        for(Vote v : list){
            int state;
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
