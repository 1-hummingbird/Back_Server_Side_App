package com.hummingbird.kr.starbuckslike.member.vo.in;

import java.time.LocalDate;
import lombok.Getter;

@Getter
public class MemberUpdateRequestVO {
    private String name;
    private String nickname;
    private LocalDate birthDate;
    private String phone;
    private String email;
}
