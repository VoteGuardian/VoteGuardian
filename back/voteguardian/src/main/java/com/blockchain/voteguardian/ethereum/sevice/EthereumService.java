package com.blockchain.voteguardian.ethereum.sevice;

import com.blockchain.voteguardian.user.entity.User;

public interface EthereumService {
    void createWallet(User user) throws Exception;
}
