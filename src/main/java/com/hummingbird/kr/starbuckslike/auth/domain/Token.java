package com.hummingbird.kr.starbuckslike.auth.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "refreshToken", timeToLive = 60 * 60 * 1000L)
public class Token {

    @Id
    private String tokenUID;
    private Date expires;

    @Builder
    public Token(String tokenUID, Date expires) {
        this.tokenUID = tokenUID;
        this.expires = expires;
    }
}
