package com.hummingbird.kr.starbuckslike.auth.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TokenSevice {

    private final RedisTemplate<String, Date> redisTemplate;

    //for logout Request, this is black-list of Refresh, so TTL is same as expires
    public void saveToken(String tokenUID, Date expires) {
        long ttl = (expires.getTime() - System.currentTimeMillis()) / 1000;
        redisTemplate.opsForValue().set(tokenUID, expires, ttl, TimeUnit.SECONDS);
    }

    public Date findTokenExp(String tokenUID) {
        return redisTemplate.opsForValue().get(tokenUID);
    }
}
