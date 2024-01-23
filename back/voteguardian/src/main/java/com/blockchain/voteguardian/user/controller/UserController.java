package com.blockchain.voteguardian.user.controller;

import com.blockchain.voteguardian.global.common.DtoResponse;
import com.blockchain.voteguardian.global.common.MessageResponse;
import com.blockchain.voteguardian.global.properties.ResponseProperties;
import com.blockchain.voteguardian.user.dto.UserRequest;
import com.blockchain.voteguardian.user.dto.UserResponse;
import com.blockchain.voteguardian.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ResponseProperties responseProperties;

    @PostMapping("/join")
    public ResponseEntity<MessageResponse> joinUser(@RequestBody UserRequest.Create request){
        userService.join(request);
        return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getSuccess()));
    }

    @PostMapping("/email")
    public ResponseEntity<MessageResponse> sendMail(@RequestBody UserRequest.Eamil request){
        userService.sendEmail(request);
        return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getSuccess()));
    }

    @PostMapping("/email/check")
    public ResponseEntity<MessageResponse> checkAuthCode(@RequestBody UserRequest.CheckAuthCode request){
        userService.checkAuth(request);
        return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getSuccess()));
    }

    @PostMapping("/login")
    public ResponseEntity<DtoResponse<UserResponse.login>> login(@RequestBody UserRequest.login request){
        UserResponse.login response = userService.login(request);
        if(response == null){
            ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getFail(),null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(DtoResponse.of(HttpStatus.OK, responseProperties.getSuccess(),response));
    }


}
