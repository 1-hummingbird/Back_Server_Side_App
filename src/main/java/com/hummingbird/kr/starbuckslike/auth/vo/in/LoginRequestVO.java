package com.hummingbird.kr.starbuckslike.auth.vo.in;

import com.hummingbird.kr.starbuckslike.auth.util.Cryptor;
import com.hummingbird.kr.starbuckslike.auth.dto.in.LoginRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.crypto.SecretKey;

@Getter
@Setter
@AllArgsConstructor
public class LoginRequestVO {
    private String loginID;
    private String Password;


    public LoginRequestDTO toDTO(){
        LoginRequestDTO tempDTO = new LoginRequestDTO();
        tempDTO.setLoginID(this.getLoginID());
        tempDTO.setPassword(this.getPassword());
        return tempDTO;
    }
}
