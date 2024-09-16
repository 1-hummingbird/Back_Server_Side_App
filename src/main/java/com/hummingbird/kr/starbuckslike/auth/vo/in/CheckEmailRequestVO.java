package com.hummingbird.kr.starbuckslike.auth.vo.in;

import com.hummingbird.kr.starbuckslike.auth.dto.in.CheckEmailRequestDTO;
import lombok.Getter;


@Getter
public class CheckEmailRequestVO {
    private String email;

    public CheckEmailRequestDTO toDTO() {
        return CheckEmailRequestDTO.builder()
                .email(email)
                .build();
    }
}
