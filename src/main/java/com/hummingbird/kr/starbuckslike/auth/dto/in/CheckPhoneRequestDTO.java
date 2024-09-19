package com.hummingbird.kr.starbuckslike.auth.dto.in;

import lombok.Getter;

@Getter
public class CheckPhoneRequestDTO {
    private String phone;

    public CheckPhoneRequestDTO(String phone) {
        this.phone = phone;
    }
}
