package com.hummingbird.kr.starbuckslike.auth.vo;

import com.hummingbird.kr.starbuckslike.auth.util.Cryptor;
import com.hummingbird.kr.starbuckslike.auth.dto.LoginRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.crypto.SecretKey;

@Getter
@Setter
@AllArgsConstructor
public class LoginRequestVO {
    private String loginID;
    private String RecivedPassword;
    private SecretKey SendedKey;

    private String Password;
    {
        try {
            Password = Cryptor.decrypt(RecivedPassword, SendedKey);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public LoginRequestDTO toDTO(){
        LoginRequestDTO tempDTO = new LoginRequestDTO();
        tempDTO.setLoginID(this.getLoginID());
        tempDTO.setPassword(this.getPassword());
        return tempDTO;
    }
}
