package com.blockchain.voteguardian.user.service;

import com.blockchain.voteguardian.user.dto.UserRequest;
import com.blockchain.voteguardian.user.entity.User;
import com.blockchain.voteguardian.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public void join(UserRequest.Create request) {
        // 회원가입 시간
        Timestamp now = new Timestamp(System.currentTimeMillis());
        //블록체인 지값 생성
        String wallet = "123";
        User user = User.UserCreate(request, now, wallet);
        userRepository.save(user);
    }
}
