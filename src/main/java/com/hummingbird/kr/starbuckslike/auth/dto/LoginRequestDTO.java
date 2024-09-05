package com.hummingbird.kr.starbuckslike.auth.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String loginID;
    private String password;
}