package com.hummingbird.kr.starbuckslike.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LogoutRequestDTO {
    private String token;

    public String getToken() {
        return this.token;
    }
}
