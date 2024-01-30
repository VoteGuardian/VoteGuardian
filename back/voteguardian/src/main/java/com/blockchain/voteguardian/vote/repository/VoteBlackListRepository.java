package com.blockchain.voteguardian.vote.repository;

import com.blockchain.voteguardian.vote.entity.VoteBlackList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteBlackListRepository extends JpaRepository<VoteBlackList, Long> {
}
