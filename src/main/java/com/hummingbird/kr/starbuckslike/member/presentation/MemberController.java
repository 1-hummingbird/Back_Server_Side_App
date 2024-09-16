package com.hummingbird.kr.starbuckslike.member.presentation;

import com.hummingbird.kr.starbuckslike.member.application.MemberService;
import com.hummingbird.kr.starbuckslike.member.vo.out.*;
import com.hummingbird.kr.starbuckslike.member.vo.in.*;
import com.hummingbird.kr.starbuckslike.member.dto.out.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public PassageResponseVO passage(@RequestBody PassageRequestVO passageRequestVO) {
        PassageResponseDTO passageResponseDTO = memberService.passage(passageRequestVO.toDTO());
        return passageResponseDTO.toVO();
    }
}
