package com.hummingbird.kr.starbuckslike.member.dto.in;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.lang.Nullable;

@Setter
@Getter
@NoArgsConstructor
public class MemberUpdateRequestDTO {

    private String memberUID;
    private @Nullable String name;
    private @Nullable String nickname;
    private @Nullable LocalDate birthDate;
    private @Nullable String phone;
    private @Nullable String email;

    
}
