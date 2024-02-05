package com.blockchain.voteguardian.voter.repository;

import com.blockchain.voteguardian.vote.entity.Vote;
import com.blockchain.voteguardian.voter.entity.Voter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.sql.Timestamp;
import java.util.List;

public interface VoterRepository extends JpaRepository<Voter, Long> {

    public interface VoterRepositoryCustom {

        List<Vote> PreList(String email, Pageable pageable, Timestamp now);
        List<Vote> ProgressList(String email, Pageable pageable, Timestamp now);
        List<Vote> EndList(String email, Pageable pageable, Timestamp now);
        int PreVoteCnt(String email, Timestamp now);
        int ProgressVoteCnt(String email, Timestamp now);
        int EndVoteCnt(String email, Timestamp now);

    }
}
