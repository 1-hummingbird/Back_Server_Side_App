package com.hummingbird.kr.starbuckslike.member.vo.out;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

import com.hummingbird.kr.starbuckslike.member.domain.MemberInfo;

@Getter
@AllArgsConstructor
public class FindMemberResponseVO {
    private List<MemberInfo> memberInfoList;
}