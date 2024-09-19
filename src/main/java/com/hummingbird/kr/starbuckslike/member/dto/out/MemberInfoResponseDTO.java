package com.hummingbird.kr.starbuckslike.member.dto.out;

import com.hummingbird.kr.starbuckslike.member.domain.MemberInfo;
import com.hummingbird.kr.starbuckslike.member.vo.out.MemberInfoResponseVO;

public class MemberInfoResponseDTO {
    
    private MemberInfo memberInfo;

    public MemberInfoResponseDTO(MemberInfo memberInfo) {
        this.memberInfo = memberInfo;
    }

    public MemberInfoResponseVO toVO() {
        return new MemberInfoResponseVO(this.memberInfo);
    }
}
