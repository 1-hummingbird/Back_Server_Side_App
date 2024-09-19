package com.hummingbird.kr.starbuckslike.member.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.hummingbird.kr.starbuckslike.member.infrastructrue.MemberRepository;
import com.hummingbird.kr.starbuckslike.member.domain.Member;
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
    public PassageResponseDTO passage(PassageRequestDTO passageRequestDTO) {
        Member member = memberRepository.findByMemberUID(passageRequestDTO.getMemberUID()).orElseThrow(() -> new RuntimeException("Member not found"));
        LocalDateTime createdAt = member.getCreatedAt();
        LocalDateTime currentTime = LocalDateTime.now();
        long daysBetween = ChronoUnit.DAYS.between(createdAt, currentTime);
        log.info("passage daysBetween: {}", daysBetween);
        return new PassageResponseDTO(member.getMemberUID(), daysBetween);
    }

    @Override
    public PurchaseResponseDTO purchase(PurchaseRequestDTO requestDTO) {
        return null;
    }

    @Override
    public void refund(RefundRequestDTO requestDTO) {

    }

    @Override
    public CanReviewResponseDTO canReview(CanReviewRequestDTO requestDTO) {
        return null;
    }

    @Override
    public void update(MemberUpdateRequestDTO requestDTO) {

    }

    @Override
    public MemberInfoResponseDTO info(MemberInfoRequestDTO requestDTO) {
        return null;
    }
}
