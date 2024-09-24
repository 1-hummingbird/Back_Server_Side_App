package com.hummingbird.kr.starbuckslike.auth.vo.in;

import com.hummingbird.kr.starbuckslike.auth.dto.in.CheckPhoneRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CheckPhoneRequestVO {
    private String phone;

    public CheckPhoneRequestDTO toDTO() {
        return new CheckPhoneRequestDTO(this.phone);
    }
}
