package com.blockchain.voteguardian.vote.repository;

import com.blockchain.voteguardian.user.entity.User;
import com.blockchain.voteguardian.vote.entity.Vote;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.sql.Timestamp;
import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    public interface VoteRepositoryCustom {
        List<Vote> mainTotalList(User user, Pageable pageable);
        List<Vote> mainPreList(User user, Pageable pageable, Timestamp now);
        List<Vote> mainProgressList(User user, Pageable pageable, Timestamp now);
        List<Vote> mainEndList(User user, Pageable pageable, Timestamp now);
        int mainTotalVoteCnt(User user);
        int mainPreVoteCnt(User user, Timestamp now);
        int mainProgressVoteCnt(User user, Timestamp now);
        int mainEndVoteCnt(User user, Timestamp now);
    }
}
