package com.hummingbird.kr.starbuckslike.member.vo.in;

import java.time.LocalDate;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
public class MemberUpdateRequestVO {
    private @Nullable String name;
    private @Nullable String nickname;
    private @Nullable LocalDate birthDate;
    private @Nullable String phone;
    private @Nullable String email;
}
