package com.hummingbird.kr.starbuckslike.member.vo.in;

import com.hummingbird.kr.starbuckslike.member.dto.in.MemberInfoRequestDTO;
import lombok.Getter;

@Getter
public class MemberInfoRequestVO {
    private String memberUID;

    public MemberInfoRequestDTO toDTO() {
        return new MemberInfoRequestDTO(memberUID);
    }
}
