package com.hummingbird.kr.starbuckslike.member.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hummingbird.kr.starbuckslike.member.infrastructrue.MemberRepository;
import com.hummingbird.kr.starbuckslike.member.domain.*;
import com.hummingbird.kr.starbuckslike.member.dto.out.*;
import com.hummingbird.kr.starbuckslike.member.dto.in.*;
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
        Member member = memberRepository.findByMemberUID(memberUID).orElseThrow(() -> new RuntimeException("Member not found"));
        LocalDateTime createdAt = member.getCreatedAt();
        LocalDateTime currentTime = LocalDateTime.now();
        long daysBetween = ChronoUnit.DAYS.between(createdAt, currentTime);
        return new PassageResponseDTO(member.getMemberUID(), daysBetween);
    }

    @Override
    public void update(MemberUpdateRequestDTO requestDTO) {
        Member oldMember = memberRepository.findByMemberUID(requestDTO.getMemberUID())
            .orElseThrow(() -> new RuntimeException("Member not found"));
        MemberUpdate memberUpdate = MemberUpdate.builder()
            .memberId(oldMember.getId())
            .loginID(oldMember.getLoginID())
            .name(requestDTO.getName() == null ? oldMember.getName() : requestDTO.getName())
            .nickname(requestDTO.getNickname() == null ? oldMember.getNickname() : requestDTO.getNickname())
            .birthdate(requestDTO.getBirthDate() == null ? oldMember.getBirthdate() : requestDTO.getBirthDate())
            .phone(requestDTO.getPhone() == null ? oldMember.getPhone() : requestDTO.getPhone())
            .email(requestDTO.getEmail() == null ? oldMember.getEmail() : requestDTO.getEmail())
            .password(oldMember.getPassword())
            .isDeleted(oldMember.getIsDeleted())
            .memberUID(oldMember.getMemberUID())
            .build();
        Member newMember = new Member(memberUpdate);
        memberRepository.save(newMember);

    }

    @Override
    public MemberInfoResponseDTO info(String memberUID) {
        MemberInfo memberInfo = new MemberInfo(memberRepository.findByMemberUID(memberUID)
            .orElseThrow(() -> new RuntimeException("Member not found")));
        return new MemberInfoResponseDTO(memberInfo);
    }

    @Override
    public FindMemberResponseDTO findMember(FindMemberRequestDTO requestDTO) {
        return null;
    }
}
