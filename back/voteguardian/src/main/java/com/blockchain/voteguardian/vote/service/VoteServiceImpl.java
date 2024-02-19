package com.blockchain.voteguardian.vote.service;

import com.blockchain.voteguardian.candidate.dto.CandidateResponse;
import com.blockchain.voteguardian.candidate.dto.Tag;
import com.blockchain.voteguardian.candidate.entity.Candidate;
import com.blockchain.voteguardian.candidate.repository.CandidateRepository;
import com.blockchain.voteguardian.candidate.service.CandidateService;
import com.blockchain.voteguardian.global.error.exception.VoteApiException;
import com.blockchain.voteguardian.global.error.model.UserErrorCode;
import com.blockchain.voteguardian.global.error.model.VoteErrorCode;
import com.blockchain.voteguardian.global.properties.ConstProperties;
import com.blockchain.voteguardian.global.properties.EncrptProperties;
import com.blockchain.voteguardian.user.entity.User;
import com.blockchain.voteguardian.user.repository.UserRepository;
import com.blockchain.voteguardian.util.AesUtil;
import com.blockchain.voteguardian.vote.dto.MainVote;
import com.blockchain.voteguardian.vote.dto.ParticipateVote;
import com.blockchain.voteguardian.vote.dto.VoteRequest;
import com.blockchain.voteguardian.vote.dto.VoteResponse;
import com.blockchain.voteguardian.vote.entity.Vote;
import com.blockchain.voteguardian.vote.repository.VoteBlackListRepository;
import com.blockchain.voteguardian.vote.repository.VoteRepository;
import com.blockchain.voteguardian.vote.repository.VoteRepositoryImpl;
import com.blockchain.voteguardian.votehistory.entity.VoteHistory;
import com.blockchain.voteguardian.votehistory.repository.VoteHistoryRepository;
import com.blockchain.voteguardian.voter.entity.Voter;
import com.blockchain.voteguardian.voter.repository.VoterRepository;
import com.blockchain.voteguardian.voter.repository.VoterRepositoryImpl;
import com.blockchain.voteguardian.voter.service.VoterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Type;
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
    private final EncrptProperties encrptProperties;
    private final VoteBlackListRepository voteBlackListRepository;
    private final CandidateRepository candidateRepository;
    private final VoterRepository voterRepository;

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
    public VoteResponse.ParticipateVoteList participateVoteList(int state, int page, String email) throws Exception {
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

    @Override
    public VoteResponse.LinkVoteDetail linkVoteDetail(long voteId) {
        if(!voteRepository.existsById(voteId)){
            throw new VoteApiException(VoteErrorCode.VOTE_DOES_NOT_EXIST);
        }
        if(voteBlackListRepository.existsById(voteId)){
            throw new VoteApiException(VoteErrorCode.VOTE_HAS_BEEN_DELETED);
        }
        Vote vote = voteRepository.findById(voteId).get();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        if(vote.getFinishAt().equals(now) || vote.getFinishAt().before(now)){
            throw new VoteApiException(VoteErrorCode.VOTE_HAS_BEEN_ENDED);
        }
        List<Candidate> candidates = candidateRepository.findAllByVote_VoteId(voteId);
        List<CandidateResponse.Detail> candidateList = new ArrayList<>();
        for(Candidate c : candidates){
            ArrayList<Tag> tagList = null;
            if(c.getTag() != null){
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<Tag>>() {}.getType();
                tagList = gson.fromJson(c.getTag(), type);
            }
            CandidateResponse.Detail d = CandidateResponse.Detail.build(c, tagList);
            candidateList.add(d);
        }

        return VoteResponse.LinkVoteDetail.build(vote, candidateList);
    }

    @Override
    public VoteResponse.Result voteResult(long voteId, String email) {
        if(!voteRepository.existsById(voteId)){
            throw new VoteApiException(VoteErrorCode.VOTE_DOES_NOT_EXIST);
        }
        if(voteBlackListRepository.existsById(voteId)){
            throw new VoteApiException(VoteErrorCode.VOTE_HAS_BEEN_DELETED);
        }
        Vote vote = voteRepository.findById(voteId).get();

        Timestamp now = new Timestamp(System.currentTimeMillis());
        // 아직 투표가 종료되지 않았을 때
        if(vote.getFinishAt().after(now)){
            throw new VoteApiException(VoteErrorCode.VOTE_HAS_NOT_ENDED);
        }

        // 투표의 투표자 목록에 해당 이메일이 존재하지 않을 때
        Voter voter = voterRepository.findByVote_VoteIdAndEmail(vote.getVoteId(), email);
        if(voter == null){
            throw new VoteApiException(VoteErrorCode.DOES_NOT_HAVE_EXACTLY_VALUES);
        }
        int totalVoterCount = voterRepository.findByVote_VoteId(vote.getVoteId()).size();

        List<Candidate> candidates = candidateRepository.findAllByVote_VoteId(voteId);
        List<CandidateResponse.Result> candidateList = new ArrayList<>();
        int attend_sum = 0;
        double percent_sum = 0;
        for(Candidate c : candidates){
            int count = voteHistoryRepository.findByVote_VoteIdAndVotedId(vote.getVoteId(), c.getCandidateId()).size();
            attend_sum += count;
            double percent = Math.round((((float)count/totalVoterCount)*100)*100.0)/100.0;
            percent_sum += percent;
            candidateList.add(CandidateResponse.Result.build(c.getNum(),c.getName(), count, percent));
        }
        // 기권표 존재
        int abstain_count = voteHistoryRepository.findByVote_VoteIdAndVotedId(vote.getVoteId(), (long) -1).size();
        if(abstain_count != 0){
            attend_sum += abstain_count;
            double percent = Math.round((((float)abstain_count/totalVoterCount)*100)*100.0)/100.0;
            percent_sum += percent;
            candidateList.add(CandidateResponse.Result.build(-1,"기권", abstain_count, percent));
        }
        // 무응답자 존재
        if(totalVoterCount != attend_sum){
            candidateList.add(CandidateResponse.Result.build(-1,"무응답", totalVoterCount-attend_sum, 100.0-percent_sum));
        }


        return VoteResponse.Result.build(vote, candidateList);
    }

    private List<ParticipateVote> changeParticipateVote(List<Vote> list, User user) throws Exception {
        List<ParticipateVote> res = new ArrayList<>();
        AesUtil aesUtil = new AesUtil(encrptProperties.getKey());
        String encryptEmail = aesUtil.AesCBCEncode(user.getEmail());
        System.out.println("encryptEmail : "+encryptEmail);
        for(Vote v : list){
            boolean participate = false;
            VoteHistory voteHistory = voteHistoryRepository.findByEncryptEmailAndVote_VoteId(encryptEmail, v.getVoteId());
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
