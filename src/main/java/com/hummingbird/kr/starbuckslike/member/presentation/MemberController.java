package com.hummingbird.kr.starbuckslike.member.presentation;

import com.hummingbird.kr.starbuckslike.auth.domain.AuthUserDetail;
import com.hummingbird.kr.starbuckslike.common.entity.BaseResponse;
import com.hummingbird.kr.starbuckslike.common.entity.BaseResponseStatus;
import com.hummingbird.kr.starbuckslike.member.application.MemberService;
import com.hummingbird.kr.starbuckslike.member.dto.in.*;
import com.hummingbird.kr.starbuckslike.member.vo.out.*;
import com.hummingbird.kr.starbuckslike.member.vo.in.*;
import com.hummingbird.kr.starbuckslike.member.dto.out.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public BaseResponse<PassageResponseVO> passage(@AuthenticationPrincipal AuthUserDetail authUserDetail) {
        log.info("passage memberUID: {}", authUserDetail.getUsername());
        PassageResponseDTO passageResponseDTO = memberService.passage(authUserDetail.getUsername());
        return new BaseResponse<>(passageResponseDTO.toVO());
    }

    @PostMapping("info")
    @Operation(security = @SecurityRequirement(name = "Bearer Auth")
            , summary = "Member information API", description = "Member information API", tags = {"Member"})
    public BaseResponse<MemberInfoResponseVO> info(@AuthenticationPrincipal AuthUserDetail authUserDetail) {
        log.info("info memberUID: {}", authUserDetail.getUsername());
        MemberInfoResponseDTO memberInfoResponseDTO = memberService.info(authUserDetail.getUsername());
        return new BaseResponse<>(MemberInfoResponseDTO.toVO(memberInfoResponseDTO));
    }

    @PostMapping("update")
    @Operation(security = @SecurityRequirement(name = "Bearer Auth")
            , summary = "Member information update API", description = "Member information update API", tags = {"Member"})
    public BaseResponse<Void> update(@RequestBody MemberUpdateRequestVO memberUpdateRequestVO,
                                     @AuthenticationPrincipal AuthUserDetail authUserDetail) {
        memberService.update(MemberUpdateRequestDTO.of(memberUpdateRequestVO, authUserDetail.getUsername()));
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

}
