package com.hummingbird.kr.starbuckslike.member.dto.out;

import com.hummingbird.kr.starbuckslike.member.domain.MemberInfo;
import com.hummingbird.kr.starbuckslike.member.vo.out.FindMemberResponseVO;

import java.util.List;

public class FindMemberResponseDTO {
    private List<MemberInfo> members;

    public FindMemberResponseVO toVO(){
        return new FindMemberResponseVO(members);
    }
}
