package com.hummingbird.kr.starbuckslike.auth.dto.out;

import com.hummingbird.kr.starbuckslike.auth.vo.out.LoginResponseVO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDTO {
    private String accessToken;
    //    private String refreshToken;
    private String name;
    private String nickName;

    public LoginResponseVO toVO(){
        return new LoginResponseVO(this.accessToken, this.name, this.nickName);
    }
}