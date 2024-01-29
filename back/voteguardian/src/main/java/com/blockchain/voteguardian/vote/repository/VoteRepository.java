package com.blockchain.voteguardian.vote.repository;

import com.blockchain.voteguardian.vote.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
