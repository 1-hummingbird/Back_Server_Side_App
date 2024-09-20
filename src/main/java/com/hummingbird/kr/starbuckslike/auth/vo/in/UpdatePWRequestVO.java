package com.hummingbird.kr.starbuckslike.auth.vo.in;

import lombok.Getter;

@Getter
public class UpdatePWRequestVO {
    private String newPassword;
    private String oldPassword;
}
