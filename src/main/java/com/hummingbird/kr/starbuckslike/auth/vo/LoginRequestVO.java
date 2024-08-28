package com.hummingbird.kr.starbuckslike.auth.vo;

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
    private String RecivedPassword;
    private String EnvelopeCode;

    private String ExtractPW(){
        String a = this.RecivedPassword;
        return a;
    }
}
