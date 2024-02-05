package com.blockchain.voteguardian.votehistory.service;

import com.blockchain.voteguardian.votehistory.dto.VoteHistoryRequest;

public interface VoteHistoryService {

    void participateVote(VoteHistoryRequest.Create request);
}
