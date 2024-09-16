package com.hummingbird.kr.starbuckslike.auth.vo.in;

import com.hummingbird.kr.starbuckslike.auth.dto.in.LogoutRequestDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutRequestVO {
    private String accessToken;
    private String refreshToken;

    public LogoutRequestDTO toDTO() {
        return new LogoutRequestDTO(accessToken, refreshToken);
    }
}