package com.hummingbird.kr.starbuckslike.auth.vo.out;

import lombok.*;

@Getter
@AllArgsConstructor
public class LoginResponseVO {

    private String accessToken;
    //    private String refreshToken;
    private String name;
    private String nickName;

}
