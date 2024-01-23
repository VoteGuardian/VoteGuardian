package com.blockchain.voteguardian.user.repository;

import com.blockchain.voteguardian.user.entity.UserBlackList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBlackListRepository extends JpaRepository<UserBlackList, Long> {
    List<UserBlackList> findByUser_Email(String email);
}
