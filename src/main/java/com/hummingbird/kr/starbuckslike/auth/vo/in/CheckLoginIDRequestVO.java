package com.hummingbird.kr.starbuckslike.auth.vo.in;

import com.hummingbird.kr.starbuckslike.auth.dto.in.CheckLoginIDRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CheckLoginIDRequestVO {
    private String loginID;

    public CheckLoginIDRequestDTO toDTO() {
        return new CheckLoginIDRequestDTO(this.loginID);
    }
}
