package com.hummingbird.kr.starbuckslike.auth.vo.in;

import com.hummingbird.kr.starbuckslike.auth.dto.in.FindIDRequestDTO;
import lombok.Getter;

@Getter
public class FindIDRequestVO {
    private String name;
    private String phoneNumber;
    private String email;

    public FindIDRequestDTO toDTO() {
        return new FindIDRequestDTO(name, phoneNumber, email);
    }
}
