package com.hummingbird.kr.starbuckslike.member.dto.out;

import com.hummingbird.kr.starbuckslike.member.vo.out.PassageResponseVO;
import lombok.Getter;

@Getter
public class PassageResponseDTO {
    private String memberUID;
    private long dateDiff;

    public PassageResponseDTO(String memberUID, Long dateDiff) {
        this.memberUID = memberUID;
        this.dateDiff = dateDiff;
    }

    public PassageResponseVO toVO() {
        return new PassageResponseVO(memberUID, dateDiff);
    }
}
