package com.blockchain.voteguardian.candidate.service;

import com.blockchain.voteguardian.candidate.dto.CandidateRequest;
import com.blockchain.voteguardian.vote.entity.Vote;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface CandidateService {
    void create(List<CandidateRequest.Create> candidateList, List<MultipartFile> photos, Vote vote) throws JsonProcessingException;
}
