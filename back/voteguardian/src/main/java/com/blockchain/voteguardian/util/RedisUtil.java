package com.blockchain.voteguardian.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisUtil {
    // StringRedisTemplate 의존성 주입받아 사용
    private final StringRedisTemplate stringRedisTemplate;

    // 키로 redis에 저장한 값 반환
    public String getData(String key){
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        return valueOperations.get(key);
    }
    // key, value 형태로 redis에 저장
    public void setDataExpire(String key,String value){
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set(key,value);
    }
}
