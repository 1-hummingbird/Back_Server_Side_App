package com.hummingbird.kr.starbuckslike.member.application;

import com.hummingbird.kr.starbuckslike.member.dto.in.*;
import com.hummingbird.kr.starbuckslike.member.dto.out.*;

public interface MemberService {

    /**
     * 회원 가입 후 경과 기간 확인
     * @param requestDTO
     * @return DTO that has memberUID and DateDiff
     */
    PassageResponseDTO passage(PassageRequestDTO requestDTO);

    /**
     * 구매 내역 조회
     * @param requestDTO
     * @return DTO that has memberUID and List<Long> purchasedProductIds
     */
    PurchaseResponseDTO purchase(PurchaseRequestDTO requestDTO);

    /**
     * 환불 요청
     * @param requestDTO
     */
    void refund(RefundRequestDTO requestDTO);

    /**
     * 리뷰 작성 가능한 상품 조회
     * @param requestDTO
     * @return DTO that has memberUID and List<Long> purchasedProductIds
     */
    CanReviewResponseDTO canReview(CanReviewRequestDTO requestDTO);

    /**
     * 회원 정보 수정
     * @param requestDTO
     */
    void update(MemberUpdateRequestDTO requestDTO);

    /**
     * 회원 정보 조회
     * @param requestDTO
     * @return DTO that has memberUID and MemberInfo
     */
    MemberInfoResponseDTO info(MemberInfoRequestDTO requestDTO);
    
}
