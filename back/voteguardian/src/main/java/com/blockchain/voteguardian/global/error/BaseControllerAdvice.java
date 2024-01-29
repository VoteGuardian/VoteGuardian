package com.blockchain.voteguardian.global.error;
import com.blockchain.voteguardian.global.common.ErrorResponse;
import com.blockchain.voteguardian.global.error.exception.UserApiException;
import com.blockchain.voteguardian.global.error.exception.VoteApiException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
@Slf4j
public class BaseControllerAdvice {
    @ExceptionHandler(UserApiException.class)
    public ResponseEntity<ErrorResponse> UserApiException(UserApiException e, HttpServletRequest req) {
        log.error(req.getRequestURI());
        log.error(e.getClass().getCanonicalName());
        e.printStackTrace();
        log.error(e.getMessage());

        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(ErrorResponse.of(e.getErrorCode()));
    }

    @ExceptionHandler(VoteApiException.class)
    public ResponseEntity<ErrorResponse> VoteApiException(VoteApiException e, HttpServletRequest req) {
        log.error(req.getRequestURI());
        log.error(e.getClass().getCanonicalName());
        e.printStackTrace();
        log.error(e.getMessage());

        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(ErrorResponse.of(e.getErrorCode()));
    }

}
