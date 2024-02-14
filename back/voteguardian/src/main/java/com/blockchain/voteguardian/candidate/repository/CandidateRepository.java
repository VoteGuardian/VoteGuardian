package com.blockchain.voteguardian.candidate.repository;

import com.blockchain.voteguardian.candidate.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Candidate findByCandidateIdAndVote_VoteId(long candidateId, Long voteId);

    List<Candidate> findAllByVote_VoteId(long voteId);
}
