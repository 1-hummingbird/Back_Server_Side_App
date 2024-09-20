package com.hummingbird.kr.starbuckslike.auth.dto.in;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdatePWRequestDTO {
    private String newPassword;
    private String oldPassword;
    private String memberUID;
}
