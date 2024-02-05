package com.blockchain.voteguardian.votehistory.repository;

import com.blockchain.voteguardian.votehistory.entity.VoteHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteHistoryRepository extends JpaRepository<VoteHistory, Long> {

    VoteHistory findByEncryptUserIdAndVote_VoteId(String encryptUserId, long voteId);
}
