package com.hummingbird.kr.starbuckslike.auth.vo.in;

import com.hummingbird.kr.starbuckslike.auth.dto.in.ResetPWRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResetPWRequestVO {
    private String loginID;
    private String password;
    private String email;

    public ResetPWRequestDTO toDTO() {
        return new ResetPWRequestDTO(this.loginID, this.password, this.email);
    }

}
