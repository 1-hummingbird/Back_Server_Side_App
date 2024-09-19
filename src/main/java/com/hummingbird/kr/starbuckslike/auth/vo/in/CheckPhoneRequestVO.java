package com.hummingbird.kr.starbuckslike.auth.vo.in;

import com.hummingbird.kr.starbuckslike.auth.dto.in.CheckPhoneRequestDTO;
import lombok.Getter;

@Getter
public class CheckPhoneRequestVO {
    private String phone;

    public CheckPhoneRequestDTO toDTO() {
        return new CheckPhoneRequestDTO(this.phone);
    }
}
