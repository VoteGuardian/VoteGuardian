package com.blockchain.voteguardian.user.service;

import com.blockchain.voteguardian.global.error.exception.UserApiException;
import com.blockchain.voteguardian.global.error.model.UserErrorCode;
import com.blockchain.voteguardian.user.dto.UserRequest;
import com.blockchain.voteguardian.user.entity.User;
import com.blockchain.voteguardian.user.entity.UserBlackList;
import com.blockchain.voteguardian.user.repository.UserBlackListRepository;
import com.blockchain.voteguardian.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserBlackListRepository userBlackListRepository;
    private final MailService mailService;

    @Override
    public void join(UserRequest.Create request) {
        // 회원가입 시간
        Timestamp now = new Timestamp(System.currentTimeMillis());
        //블록체인 지값 생성
        String wallet = "123";
        User user = User.UserCreate(request, now, wallet);
        userRepository.save(user);
    }

    @Override
    public void sendEmail(UserRequest.Eamil request) {
        List<User> userList = userRepository.findByEmail(request.getEmail());
        List<UserBlackList> userBlackLists = userBlackListRepository.findByUser_Email(request.getEmail());

        // 이미 가입되어 있는 회원
        if(userList.size() != userBlackLists.size()){
            throw new UserApiException(UserErrorCode.EMAIL_ALREADY_EXISTS);
        }

        // 이메일 전송하기
        mailService.sendMail(request.getEmail());

    }
}
