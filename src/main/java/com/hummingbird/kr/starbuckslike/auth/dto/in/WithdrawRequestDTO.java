package com.hummingbird.kr.starbuckslike.auth.dto.in;

import lombok.Getter;

@Getter
public class WithdrawRequestDTO {
    private String memberUID;

    public WithdrawRequestDTO(String memberUID) {
        this.memberUID = memberUID;
    }
}
