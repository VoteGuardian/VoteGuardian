package com.blockchain.voteguardian.user.service;

import com.blockchain.voteguardian.user.dto.UserResponse;
import com.blockchain.voteguardian.util.RedisUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional
@RequiredArgsConstructor
@PropertySources({
        @PropertySource("classpath:application-personal.properties")
})
public class MailServiceImpl implements MailService{

    private final RedisUtil redisUtil;

    private final JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String from_email = "";
    @Override
    public void sendMail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        String authKey="";
        message.setFrom(from_email);

        message.setTo(email);
        message.setSubject("가입 이메일 인증번호입니다.");

        //인증키 6자리 랜덤으로 생성 후 초기화
        authKey = Integer.toString( ThreadLocalRandom.current().nextInt(100000, 1000000) );

        message.setText("인증번호는 " + authKey + "입니다.\n인증번호를 입력해주세요.");
        emailSender.send(message);
        redisUtil.setDataExpire(email,authKey);
    }
}
