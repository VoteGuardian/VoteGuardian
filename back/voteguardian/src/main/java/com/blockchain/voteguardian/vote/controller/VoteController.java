package com.blockchain.voteguardian.vote.controller;

import com.blockchain.voteguardian.global.common.DtoResponse;
import com.blockchain.voteguardian.global.common.MessageResponse;
import com.blockchain.voteguardian.global.error.exception.VoteApiException;
import com.blockchain.voteguardian.global.error.model.VoteErrorCode;
import com.blockchain.voteguardian.global.properties.ResponseProperties;
import com.blockchain.voteguardian.vote.dto.VoteRequest;
import com.blockchain.voteguardian.vote.dto.VoteResponse;
import com.blockchain.voteguardian.vote.service.VoteService;
import com.blockchain.voteguardian.votehistory.dto.VoteHistoryRequest;
import com.blockchain.voteguardian.votehistory.service.VoteHistoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vote")
public class VoteController {

    private final VoteService voteService;
    private final ResponseProperties responseProperties;
    private final VoteHistoryService voteHistoryService;

    @PostMapping("")
    public ResponseEntity<DtoResponse<VoteResponse.CreateVote>> create(@Validated  @RequestPart(value = "request") VoteRequest.Create request, BindingResult bindingResult,
                                                                       @RequestPart(value = "photos", required = false) List<MultipartFile> photos) throws JsonProcessingException {
        if(bindingResult.hasErrors()){
            throw new VoteApiException(VoteErrorCode.VOTE_DOES_NOT_HAVE_EXACTLY_VALUES);
        }
        VoteResponse.CreateVote response = voteService.create(request, photos);
        if(response == null){
            ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getFail(),null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(),response));
    }

    @GetMapping("/main")
    public ResponseEntity<DtoResponse<VoteResponse.MainVoteList>> mainVoteList(int state, int page, String email){
        VoteResponse.MainVoteList response = voteService.mainVoteList(state, page, email);
        if(response == null){
            ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getFail(),null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(),response));
    }

    @GetMapping("/link")
    public ResponseEntity<DtoResponse<VoteResponse.ParticipateVoteList>> participateVoteList(int state, int page, String email) throws Exception {
        VoteResponse.ParticipateVoteList response = voteService.participateVoteList(state, page, email);
        if(response == null){
            ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getFail(),null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(),response));
    }

    @PostMapping("/link")
    public ResponseEntity<MessageResponse> participateVote(@RequestBody VoteHistoryRequest.Create request, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            throw new VoteApiException(VoteErrorCode.DOES_NOT_HAVE_EXACTLY_VALUES);
        }
        voteHistoryService.participateVote(request);
        return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getSuccess()));
    }

    @GetMapping("/link/detail")
    public ResponseEntity<DtoResponse<VoteResponse.LinkVoteDetail>> linkVoteDetail(long vote) {
        VoteResponse.LinkVoteDetail response = voteService.linkVoteDetail(vote);
        if(response == null){
            ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getFail(),null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(),response));
    }
}
