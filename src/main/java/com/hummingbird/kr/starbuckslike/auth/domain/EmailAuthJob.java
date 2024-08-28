package com.hummingbird.kr.starbuckslike.auth.domain;

import java.time.LocalDateTime;

public class EmailAuthJob {
    private String email;
    private String authcode;
    private LocalDateTime expireTime;

    public EmailAuthJob(String email, String authcode, LocalDateTime expireTime) {
        this.email = email;
        this.authcode = authcode;
        this.expireTime = expireTime;
    }

    public String getEmail() {
        return email;
    }

    public String getAuthcode() {
        return authcode;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }
}
