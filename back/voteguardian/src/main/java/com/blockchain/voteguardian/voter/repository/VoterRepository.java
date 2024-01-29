package com.blockchain.voteguardian.voter.repository;

import com.blockchain.voteguardian.voter.entity.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoterRepository extends JpaRepository<Voter, Long> {
}
