package com.blockchain.voteguardian.ethereum.sevice;

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
    public String createWallet() throws Exception {
        String seed = UUID.randomUUID().toString();
        ECKeyPair ecKeyPair = Keys.createEcKeyPair();
        WalletFile aWallet = org.web3j.crypto.Wallet.createLight(seed, ecKeyPair);
        String address = "0x" + aWallet.getAddress();

        return address;
    }
}
