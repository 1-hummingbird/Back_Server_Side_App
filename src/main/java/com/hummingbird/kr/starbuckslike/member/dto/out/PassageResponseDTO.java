package com.hummingbird.kr.starbuckslike.member.dto.out;

import com.hummingbird.kr.starbuckslike.member.vo.out.PassageResponseVO;
import lombok.Getter;

@Getter
public class PassageResponseDTO {
    private long dateDiff;

    public PassageResponseDTO(Long dateDiff) {
        this.dateDiff = dateDiff;
    }

    public PassageResponseVO toVO() {
        return new PassageResponseVO(dateDiff);
    }
}
