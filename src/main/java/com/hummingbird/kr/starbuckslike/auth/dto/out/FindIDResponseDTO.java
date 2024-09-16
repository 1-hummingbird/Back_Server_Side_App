package com.hummingbird.kr.starbuckslike.auth.dto.out;

import com.hummingbird.kr.starbuckslike.auth.vo.out.FindIDResponseVO;
import lombok.Getter;

@Getter
public class FindIDResponseDTO { 
    private String loginID;

    public FindIDResponseDTO(String loginID) {
        this.loginID = loginID;
    }
    public FindIDResponseVO toVO() {
        return new FindIDResponseVO(this.loginID);
    }
}
