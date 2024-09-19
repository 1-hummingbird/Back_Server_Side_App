package com.hummingbird.kr.starbuckslike.auth.dto.out;

import com.hummingbird.kr.starbuckslike.auth.vo.out.CheckPhoneResponseVO;
import lombok.Getter;

@Getter
public class CheckPhoneResponseDTO {
    private boolean isAvailable;

    public CheckPhoneResponseDTO(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public CheckPhoneResponseVO toVO() {
        return new CheckPhoneResponseVO(this.isAvailable);
    }
}