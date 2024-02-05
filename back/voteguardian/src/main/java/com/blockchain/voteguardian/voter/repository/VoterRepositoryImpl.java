package com.blockchain.voteguardian.voter.repository;


import com.blockchain.voteguardian.vote.entity.QVoteBlackList;
import com.blockchain.voteguardian.vote.entity.Vote;
import com.blockchain.voteguardian.voter.entity.QVoter;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;
import java.util.List;

@Repository
@Transactional
public class VoterRepositoryImpl implements VoterRepository.VoterRepositoryCustom{
    @Autowired
    EntityManager em;


    @Override
    public List<Vote> PreList(String email, Pageable pageable, Timestamp now) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QVoteBlackList b = new QVoteBlackList("b");
        QVoter vt = new QVoter("vt");

        return query.select(vt.vote)
                .from(vt)
                .where(vt.email.eq(email).and(vt.vote.voteId.notIn(JPAExpressions.select(b.voteId).from(b))).and(vt.vote.startAt.after(now)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(vt.vote.finishAt.asc())
                .fetch();

    }

    @Override
    public List<Vote> ProgressList(String email, Pageable pageable, Timestamp now) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QVoteBlackList b = new QVoteBlackList("b");
        QVoter vt = new QVoter("vt");

        return query.select(vt.vote)
                .from(vt)
                .where(vt.email.eq(email).and(vt.vote.voteId.notIn(JPAExpressions.select(b.voteId).from(b))).and(vt.vote.startAt.loe(now)).and(vt.vote.finishAt.after(now)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(vt.vote.finishAt.asc())
                .fetch();
    }

    @Override
    public List<Vote> EndList(String email, Pageable pageable, Timestamp now) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QVoteBlackList b = new QVoteBlackList("b");
        QVoter vt = new QVoter("vt");

        return query.select(vt.vote)
                .from(vt)
                .where(vt.email.eq(email).and(vt.vote.voteId.notIn(JPAExpressions.select(b.voteId).from(b))).and(vt.vote.finishAt.loe(now)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(vt.vote.finishAt.asc())
                .fetch();
    }

    @Override
    public int PreVoteCnt(String email, Timestamp now) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QVoteBlackList b = new QVoteBlackList("b");
        QVoter vt = new QVoter("vt");

        return query.select(vt.vote)
                .from(vt)
                .where(vt.email.eq(email).and(vt.vote.voteId.notIn(JPAExpressions.select(b.voteId).from(b))).and(vt.vote.startAt.after(now)))
                .fetch()
                .size();
    }

    @Override
    public int ProgressVoteCnt(String email, Timestamp now) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QVoteBlackList b = new QVoteBlackList("b");
        QVoter vt = new QVoter("vt");

        return query.select(vt.vote)
                .from(vt)
                .where(vt.email.eq(email).and(vt.vote.voteId.notIn(JPAExpressions.select(b.voteId).from(b))).and(vt.vote.startAt.loe(now)).and(vt.vote.finishAt.after(now)))
                .fetch()
                .size();
    }

    @Override
    public int EndVoteCnt(String email, Timestamp now) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QVoteBlackList b = new QVoteBlackList("b");
        QVoter vt = new QVoter("vt");

        return query.select(vt.vote)
                .from(vt)
                .where(vt.email.eq(email).and(vt.vote.voteId.notIn(JPAExpressions.select(b.voteId).from(b))).and(vt.vote.finishAt.loe(now)))
                .fetch()
                .size();
    }
}
