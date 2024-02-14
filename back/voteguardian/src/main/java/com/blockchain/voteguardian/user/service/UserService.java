package com.blockchain.voteguardian.user.service;

import com.blockchain.voteguardian.user.dto.UserRequest;
import com.blockchain.voteguardian.user.dto.UserResponse;


public interface UserService {
    void join(UserRequest.Create request) throws Exception;
    void sendEmail(UserRequest.Eamil request);

    void checkAuth(UserRequest.CheckAuthCode request);

    UserResponse.login login(UserRequest.login request);
}
