package com.hummingbird.kr.starbuckslike.auth.vo.in;

import com.hummingbird.kr.starbuckslike.auth.dto.in.LoginRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestVO {
    private String loginID;
    private String password;


    public LoginRequestDTO toDTO(){
        LoginRequestDTO tempDTO = new LoginRequestDTO();
        tempDTO.setLoginID(this.getLoginID());
        tempDTO.setPassword(this.getPassword());
        return tempDTO;
    }
}
