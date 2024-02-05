package com.blockchain.voteguardian.candidate.repository;

import com.blockchain.voteguardian.candidate.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Candidate findByCandidateIdAndVote_VoteId(long candidateId, Long voteId);
}
