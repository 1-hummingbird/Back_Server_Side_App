package com.hummingbird.kr.starbuckslike.auth.vo.out;

import com.hummingbird.kr.starbuckslike.auth.dto.out.LoginResponseDTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
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
