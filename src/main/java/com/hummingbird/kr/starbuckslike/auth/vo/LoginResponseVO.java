package com.hummingbird.kr.starbuckslike.auth.vo;

import com.hummingbird.kr.starbuckslike.auth.dto.LoginRequestDTO;
import com.hummingbird.kr.starbuckslike.auth.dto.LoginResponseDTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseVO {

    private String accessToken;
    //    private String refreshToken;
    private String name;
    private String uuid;

    public LoginResponseDTO toDTO(){
        LoginResponseDTO tempDTO = new LoginResponseDTO();
        tempDTO.setAccessToken(this.getAccessToken());
        tempDTO.setName(this.getName());
        tempDTO.setUuid(this.getUuid());
        return tempDTO;
    }
}
