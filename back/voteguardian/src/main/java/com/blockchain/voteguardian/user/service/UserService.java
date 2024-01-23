package com.blockchain.voteguardian.user.service;

import com.blockchain.voteguardian.user.dto.UserRequest;


public interface UserService {
    void join(UserRequest.Create request);
    void sendEmail(UserRequest.Eamil request);

    void checkAuth(UserRequest.CheckAuthCode request);
}
