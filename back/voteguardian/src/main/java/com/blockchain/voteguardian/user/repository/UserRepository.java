package com.blockchain.voteguardian.user.repository;

import com.blockchain.voteguardian.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
