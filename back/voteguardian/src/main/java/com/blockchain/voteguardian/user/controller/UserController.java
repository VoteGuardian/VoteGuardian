package com.blockchain.voteguardian.user.controller;

import com.blockchain.voteguardian.global.common.MessageResponse;
import com.blockchain.voteguardian.global.properties.ResponseProperties;
import com.blockchain.voteguardian.user.dto.UserRequest;
import com.blockchain.voteguardian.user.service.UserServiceImpl;
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

    private final UserServiceImpl userService;
    private final ResponseProperties responseProperties;
    @PostMapping("/join")
    public ResponseEntity<MessageResponse> joinUser(@RequestBody UserRequest.Create request){
        userService.join(request);
        return ResponseEntity.status(HttpStatus.OK).body(MessageResponse.of(HttpStatus.OK, responseProperties.getSuccess()));
    }

}
