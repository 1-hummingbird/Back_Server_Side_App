package com.hummingbird.kr.starbuckslike.shipping.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShippingAddressDefaultChangeDto {



    private Long id;
    private Boolean defaultAddress;


    @Builder   //빌더는 생성자에만....
    public ShippingAddressDefaultChangeDto(Long id, Boolean defaultAddress) {
        this.id = id;
        this.defaultAddress = defaultAddress;
    }
}
