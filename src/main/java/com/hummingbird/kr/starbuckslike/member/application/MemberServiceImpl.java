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
        Member member = memberRepository.findByMemberUID(memberUID)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_USER));
        LocalDateTime createdAt = member.getCreatedAt();
        LocalDateTime currentTime = LocalDateTime.now();
        long daysBetween = ChronoUnit.DAYS.between(createdAt, currentTime);
        return new PassageResponseDTO(daysBetween);
    }

    @Override
    public void update(MemberUpdateRequestDTO requestDTO) {
        Member oldMember = memberRepository.findByMemberUID(requestDTO.getMemberUID())
            .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_USER));
        // todo: 기존 회원 정보 프론트 화면에 표시 될 수 있는데
        // 그러면 nullable 하면 안 됩니다.
        // todo: toBuilder()를 쓰면 Setter랑 무슨 차이일까요?
        // Entity를 수정하게 되는데요
        // 1차 캐시 : JPA에서 메모리로
        // 2차 캐시 : updatedMember로 변수로 만들어서 계속 쓰면 써서 어플리케이션 단에서 계속 가지고 갈 거에요?
        // toEntity(에 DB서 가져온 ID 포함해서 만들어주면)
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
        Member member = memberRepository.findByMemberUID(memberUID)
            .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_USER));
        return MemberInfoResponseDTO.from(member);
    }
}
