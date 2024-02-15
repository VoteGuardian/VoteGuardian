package com.blockchain.voteguardian.votehistory.repository;

import com.blockchain.voteguardian.votehistory.entity.VoteHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteHistoryRepository extends JpaRepository<VoteHistory, Long> {

    VoteHistory findByEncryptEmailAndVote_VoteId(String encryptUserId, long voteId);


    List<VoteHistory> findByVote_VoteIdAndVotedId(Long voteId, Long candidateId);
}
