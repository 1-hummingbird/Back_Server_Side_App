package com.hummingbird.kr.starbuckslike.auth.vo.in;

import com.hummingbird.kr.starbuckslike.auth.dto.in.WithdrawRequestDTO;

import lombok.Getter;

@Getter
public class WithdrawRequestVO {
    private String memberUID;

    public WithdrawRequestDTO toDTO() {
        return new WithdrawRequestDTO(memberUID);
    }
}
