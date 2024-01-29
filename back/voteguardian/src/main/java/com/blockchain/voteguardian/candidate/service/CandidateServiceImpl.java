package com.blockchain.voteguardian.candidate.service;

import com.blockchain.voteguardian.candidate.dto.CandidateRequest;
import com.blockchain.voteguardian.candidate.entity.Candidate;
import com.blockchain.voteguardian.candidate.repository.CandidateRepository;
import com.blockchain.voteguardian.global.error.exception.VoteApiException;
import com.blockchain.voteguardian.global.error.model.VoteErrorCode;
import com.blockchain.voteguardian.vote.entity.Vote;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService{

    private final CandidateRepository candidateRepository;
    @Override
    public void create(List<CandidateRequest.Create> candidateList, List<MultipartFile> photos, Vote vote) throws JsonProcessingException {
        int photo_idx = 0;
        for(CandidateRequest.Create req : candidateList){
            String picture = null;
            if(req.isPicture()){
                if(photo_idx == photos.size()){
                    throw new VoteApiException(VoteErrorCode.VOTE_DOES_NOT_HAVE_EXACTLY_VALUES);
                }
                MultipartFile photo = photos.get(photo_idx);
                // s3 버킷에 사진 등록

                picture="url 주소";
                photo_idx++;
            }
            String tags = new ObjectMapper().writeValueAsString(req.getTagList());
            Candidate candidate = Candidate.candidateCreate(req, vote, picture, tags);
            candidateRepository.save(candidate);
        }
    }
}
