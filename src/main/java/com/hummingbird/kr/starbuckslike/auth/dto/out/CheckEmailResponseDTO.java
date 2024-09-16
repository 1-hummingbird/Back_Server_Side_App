package com.hummingbird.kr.starbuckslike.auth.dto.out;

import com.hummingbird.kr.starbuckslike.auth.vo.out.CheckEmailResponseVO;
import lombok.Getter;

@Getter
public class CheckEmailResponseDTO {
    private boolean isAvailable;

    public CheckEmailResponseDTO(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public CheckEmailResponseVO toVO() {
        return new CheckEmailResponseVO(isAvailable);
    }
}