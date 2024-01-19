package com.blockchain.voteguardian.user.service;

import com.blockchain.voteguardian.user.dto.UserRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void join(UserRequest.Create request);
}
