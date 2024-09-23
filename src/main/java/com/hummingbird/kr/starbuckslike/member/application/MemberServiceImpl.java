package com.hummingbird.kr.starbuckslike.member.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hummingbird.kr.starbuckslike.member.infrastructrue.MemberRepository;
import com.hummingbird.kr.starbuckslike.member.domain.*;
import com.hummingbird.kr.starbuckslike.member.dto.out.*;
import com.hummingbird.kr.starbuckslike.member.dto.in.*;
import com.hummingbird.kr.starbuckslike.common.Exception.BaseException;
import com.hummingbird.kr.starbuckslike.common.entity.BaseResponseStatus;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public PassageResponseDTO passage(String memberUID) {
        Member member = memberRepository.findByMemberUID(memberUID).orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_USER));
        LocalDateTime createdAt = member.getCreatedAt();
        LocalDateTime currentTime = LocalDateTime.now();
        long daysBetween = ChronoUnit.DAYS.between(createdAt, currentTime);
        return new PassageResponseDTO(member.getMemberUID(), daysBetween);
    }

    @Override
    public void update(MemberUpdateRequestDTO requestDTO) {
        Member oldMember = memberRepository.findByMemberUID(requestDTO.getMemberUID())
            .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_USER));
        
        Member updatedMember = oldMember.toBuilder()
            .name(requestDTO.getName() != null ? requestDTO.getName() : oldMember.getName())
            .nickname(requestDTO.getNickname() != null ? requestDTO.getNickname() : oldMember.getNickname())
            .birthdate(requestDTO.getBirthDate() != null ? requestDTO.getBirthDate() : oldMember.getBirthdate())
            .phone(requestDTO.getPhone() != null ? requestDTO.getPhone() : oldMember.getPhone())
            .email(requestDTO.getEmail() != null ? requestDTO.getEmail() : oldMember.getEmail())
            .build();
        
        memberRepository.save(updatedMember);
    }

    @Override
    public MemberInfoResponseDTO info(String memberUID) {
        MemberInfo memberInfo = new MemberInfo(memberRepository.findByMemberUID(memberUID)
            .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_USER)));
        return new MemberInfoResponseDTO(memberInfo);
    }

    @Override
    public FindMemberResponseDTO findMember(FindMemberRequestDTO requestDTO) {
        return null;
    }
}
