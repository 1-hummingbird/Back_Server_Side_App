package com.hummingbird.kr.starbuckslike.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResetPWRequestDTO {
    private String memberUID;
    private String newPW;

}
