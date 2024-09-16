package com.hummingbird.kr.starbuckslike.member.application;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hummingbird.kr.starbuckslike.member.infrastructrue.MemberRepository;
import com.hummingbird.kr.starbuckslike.member.domain.Member;
import com.hummingbird.kr.starbuckslike.member.dto.out.*;
import com.hummingbird.kr.starbuckslike.member.dto.in.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public PassageResponseDTO passage(PassageRequestDTO passageRequestDTO) {
        Member member = memberRepository.findByMemberUID(passageRequestDTO.getMemberUID()).orElseThrow(() -> new RuntimeException("Member not found"));
        LocalDateTime createdAt = member.getCreatedAt();
        LocalDateTime currentTime = LocalDateTime.now();
        long daysBetween = ChronoUnit.DAYS.between(createdAt, currentTime);
        return new PassageResponseDTO(member.getMemberUID(), daysBetween);
    }
}
