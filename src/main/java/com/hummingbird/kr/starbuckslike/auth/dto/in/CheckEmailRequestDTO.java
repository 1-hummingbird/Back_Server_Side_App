package com.hummingbird.kr.starbuckslike.auth.dto.in;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CheckEmailRequestDTO {
    private String email;

    public CheckEmailRequestDTO(String email) {
        this.email = email;
    }
}
