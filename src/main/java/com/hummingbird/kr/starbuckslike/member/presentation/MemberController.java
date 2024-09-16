package com.hummingbird.kr.starbuckslike.member.presentation;

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
    @Operation(security = @SecurityRequirement(name = "Bearer Auth"))
    public PassageResponseVO passage(@RequestBody PassageRequestVO passageRequestVO) {
        log.info("passage memberUID: {}", passageRequestVO.getMemberUID());
        PassageResponseDTO passageResponseDTO = memberService.passage(passageRequestVO.toDTO());
        return passageResponseDTO.toVO();
    }
}
