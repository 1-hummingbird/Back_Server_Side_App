package com.hummingbird.kr.starbuckslike.auth.dto.out;

import com.hummingbird.kr.starbuckslike.auth.vo.out.CheckLoginIDResponseVO;
import lombok.Getter;

@Getter
public class CheckLoginIDResponseDTO {
    private boolean isAvailable;

    public CheckLoginIDResponseDTO(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public CheckLoginIDResponseVO toVO() {
        return new CheckLoginIDResponseVO(this.isAvailable);
    }
}
