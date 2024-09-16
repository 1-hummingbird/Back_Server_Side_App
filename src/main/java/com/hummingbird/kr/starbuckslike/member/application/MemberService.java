package com.hummingbird.kr.starbuckslike.member.application;

import com.hummingbird.kr.starbuckslike.member.dto.in.PassageRequestDTO;
import com.hummingbird.kr.starbuckslike.member.dto.out.PassageResponseDTO;

public interface MemberService {

    /**
     * 회원 가입 후 경과 기간 확인
     * @param requestDTO
     * @return DTO that has memberUID and DateDiff
     */
    PassageResponseDTO passage(PassageRequestDTO requestDTO);
}
