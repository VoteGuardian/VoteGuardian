package com.blockchain.voteguardian.vote.repository;

import com.blockchain.voteguardian.user.entity.User;
import com.blockchain.voteguardian.vote.entity.QVote;
import com.blockchain.voteguardian.vote.entity.QVoteBlackList;
import com.blockchain.voteguardian.vote.entity.Vote;
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
public class VoteRepositoryImpl implements VoteRepository.VoteRepositoryCustom{

    @Autowired
    EntityManager em;

    @Override
    public List<Vote> mainTotalList(User user, Pageable pageable) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QVote v = new QVote("v");
        QVoteBlackList b = new QVoteBlackList("b");

        return query
                .selectFrom(v)
                .where(v.user.eq(user).and(v.voteId.notIn(JPAExpressions.select(b.voteId).from(b))))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(v.createdAt.desc())
                .fetch();
    }

    @Override
    public List<Vote> mainPreList(User user, Pageable pageable, Timestamp now) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QVote v = new QVote("v");
        QVoteBlackList b = new QVoteBlackList("b");

        return query
                .selectFrom(v)
                .where(v.user.eq(user).and(v.voteId.notIn(JPAExpressions.select(b.voteId).from(b))).and(v.startAt.after(now)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(v.finishAt.asc())
                .fetch();
    }

    @Override
    public List<Vote> mainProgressList(User user, Pageable pageable, Timestamp now) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QVote v = new QVote("v");
        QVoteBlackList b = new QVoteBlackList("b");

        return query
                .selectFrom(v)
                .where(v.user.eq(user).and(v.voteId.notIn(JPAExpressions.select(b.voteId).from(b))).and(v.startAt.loe(now)).and(v.finishAt.after(now)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(v.finishAt.asc())
                .fetch();
    }

    @Override
    public List<Vote> mainEndList(User user, Pageable pageable, Timestamp now) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QVote v = new QVote("v");
        QVoteBlackList b = new QVoteBlackList("b");

        return query
                .selectFrom(v)
                .where(v.user.eq(user).and(v.voteId.notIn(JPAExpressions.select(b.voteId).from(b))).and(v.finishAt.loe(now)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(v.finishAt.asc())
                .fetch();
    }

    @Override
    public int mainTotalVoteCnt(User user) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QVote v = new QVote("v");
        QVoteBlackList b = new QVoteBlackList("b");
        List<Vote> result = query
                .selectFrom(v)
                .where(v.user.eq(user).and(v.voteId.notIn(JPAExpressions.select(b.voteId).from(b))))
                .fetch();
        return result.size();
    }

    @Override
    public int mainPreVoteCnt(User user, Timestamp now) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QVote v = new QVote("v");
        QVoteBlackList b = new QVoteBlackList("b");
        List<Vote> result = query
                .selectFrom(v)
                .where(v.user.eq(user).and(v.voteId.notIn(JPAExpressions.select(b.voteId).from(b))).and(v.startAt.after(now)))
                .fetch();
        return result.size();
    }

    @Override
    public int mainProgressVoteCnt(User user, Timestamp now) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QVote v = new QVote("v");
        QVoteBlackList b = new QVoteBlackList("b");
        List<Vote> result = query
                .selectFrom(v)
                .where(v.user.eq(user).and(v.voteId.notIn(JPAExpressions.select(b.voteId).from(b))).and(v.startAt.loe(now)).and(v.finishAt.after(now)))
                .fetch();
        return result.size();
    }

    @Override
    public int mainEndVoteCnt(User user, Timestamp now) {
        JPAQueryFactory query = new JPAQueryFactory(em);
        QVote v = new QVote("v");
        QVoteBlackList b = new QVoteBlackList("b");
        List<Vote> result = query
                .selectFrom(v)
                .where(v.user.eq(user).and(v.voteId.notIn(JPAExpressions.select(b.voteId).from(b))).and(v.finishAt.loe(now)))
                .fetch();
        return result.size();
    }
}
