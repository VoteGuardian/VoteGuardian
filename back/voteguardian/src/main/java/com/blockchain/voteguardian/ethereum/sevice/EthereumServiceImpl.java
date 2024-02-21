package com.blockchain.voteguardian.ethereum.sevice;

import com.blockchain.voteguardian.user.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.WalletFile;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class EthereumServiceImpl implements EthereumService{


    @Override
    public void createWallet(User user) throws Exception {
        String seed = UUID.randomUUID().toString();
        ECKeyPair ecKeyPair = Keys.createEcKeyPair();
        WalletFile aWallet = org.web3j.crypto.Wallet.createLight(seed, ecKeyPair);

        String address = "0x" + aWallet.getAddress();
        String privatekey = "0x" + ecKeyPair.getPrivateKey().toString(16);

        user.WalletCreate(address, privatekey);
    }
}
