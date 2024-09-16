package com.hummingbird.kr.starbuckslike.member.presentation;

import com.hummingbird.kr.starbuckslike.common.entity.BaseResponse;
import com.hummingbird.kr.starbuckslike.common.entity.BaseResponseStatus;
import com.hummingbird.kr.starbuckslike.member.application.MemberService;
import com.hummingbird.kr.starbuckslike.member.vo.out.*;
import com.hummingbird.kr.starbuckslike.member.vo.in.*;
import com.hummingbird.kr.starbuckslike.member.dto.out.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/member")
@ComponentScan(basePackages = {"com.hummingbird.kr.starbuckslike.member"})
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("passage")
    @Operation(security = @SecurityRequirement(name = "Bearer Auth"),
            summary = "Days since enrollment API", description = "Days since enrollment API", tags = {"Member"})
    public BaseResponse<PassageResponseVO> passage(@RequestBody PassageRequestVO passageRequestVO) {
        log.info("passage memberUID: {}", passageRequestVO.getMemberUID());
        PassageResponseDTO passageResponseDTO = memberService.passage(passageRequestVO.toDTO());
        return new BaseResponse<PassageResponseVO>(passageResponseDTO.toVO());
    }

    @PostMapping("canReview")
    @Operation(security = @SecurityRequirement(name = "Bearer Auth")
            , summary = "Check if the member can review API", description = "Check if the member can review API", tags = {"Member"})
    public BaseResponse<CanReviewResponseVO> canReview(@RequestBody CanReviewRequestVO canReviewRequestVO) {
        log.info("canReview memberUID: {}", canReviewRequestVO.getMemberUID());
        CanReviewResponseDTO canReviewResponseDTO = memberService.canReview(canReviewRequestVO.toDTO());
        return new BaseResponse<CanReviewResponseVO>(canReviewResponseDTO.toVO());
    }

    @PostMapping("info")
    @Operation(security = @SecurityRequirement(name = "Bearer Auth")
            , summary = "Member information API", description = "Member information API", tags = {"Member"})
    public BaseResponse<MemberInfoResponseVO> info(@RequestBody MemberInfoRequestVO memberInfoRequestVO) {
        log.info("info memberUID: {}", memberInfoRequestVO.getMemberUID());
        MemberInfoResponseDTO memberInfoResponseDTO = memberService.info(memberInfoRequestVO.toDTO());
        return new BaseResponse<MemberInfoResponseVO>(memberInfoResponseDTO.toVO());
    }

    @PostMapping("update")
    @Operation(security = @SecurityRequirement(name = "Bearer Auth")
            , summary = "Member information update API", description = "Member information update API", tags = {"Member"})
    public BaseResponse<Void> update(@RequestBody MemberUpdateRequestVO memberUpdateRequestVO) {
        log.info("update memberUID: {}", memberUpdateRequestVO.getMemberUID());
        memberService.update(memberUpdateRequestVO.toDTO());
        return new BaseResponse<Void>(BaseResponseStatus.SUCCESS);
    }

    @PostMapping("purchase")
    @Operation(security = @SecurityRequirement(name = "Bearer Auth")
            , summary = "Purchase history API", description = "Purchase history API", tags = {"Member"})
    public BaseResponse<PurchaseResponseVO> purchase(@RequestBody PurchaseRequestVO purchaseRequestVO) {
        log.info("purchase memberUID: {}", purchaseRequestVO.getMemberUID());
        PurchaseResponseDTO purchaseResponseDTO = memberService.purchase(purchaseRequestVO.toDTO());
        return new BaseResponse<PurchaseResponseVO>(purchaseResponseDTO.toVO());
    }

    @PostMapping("refund")
    @Operation(security = @SecurityRequirement(name = "Bearer Auth")
            , summary = "Refund request API", description = "Refund request API", tags = {"Member"})
    public BaseResponse<Void> refund(@RequestBody RefundRequestVO refundRequestVO) {
        log.info("refund memberUID: {}", refundRequestVO.getMemberUID());
        memberService.refund(refundRequestVO.toDTO());
        return new BaseResponse<Void>(BaseResponseStatus.SUCCESS);
    }

}
