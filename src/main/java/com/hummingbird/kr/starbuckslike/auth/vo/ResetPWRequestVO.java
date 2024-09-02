package com.hummingbird.kr.starbuckslike.auth.vo;

import com.hummingbird.kr.starbuckslike.auth.dto.ResetPWRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResetPWRequestVO {
    private String memberUID;
    private String newPW;

    public ResetPWRequestDTO toDTO(){
        return new ResetPWRequestDTO(this.memberUID, this.newPW);
    }
}
