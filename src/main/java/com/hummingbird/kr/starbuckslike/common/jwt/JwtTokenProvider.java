package com.hummingbird.kr.starbuckslike.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class JwtTokenProvider {

    private final Environment env;

    public String generateAccessToken(Authentication authentication) {
        Claims claims = Jwts.claims().subject(authentication.getName()).build();
        Date now = new Date();
        Date expiration = new Date(now.getTime() + env.getProperty("jwt.access-expire-time", Long.class).longValue());

        return Jwts.builder()
                .signWith(getSignKey())
                .claim("email", claims.getSubject())
                .issuedAt(expiration)
                .compact();
    }

    public Key getSignKey() {
        return Keys.hmacShaKeyFor( env.getProperty("jwt.secret-key").getBytes() );
    }

}
