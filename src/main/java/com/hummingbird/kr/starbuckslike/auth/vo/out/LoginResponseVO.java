package com.hummingbird.kr.starbuckslike.auth.vo.out;

import com.hummingbird.kr.starbuckslike.auth.dto.out.LoginResponseDTO;
import lombok.*;


@AllArgsConstructor
public class LoginResponseVO {

    private String accessToken;
    //    private String refreshToken;
    private String name;
    private String uuid;

}
