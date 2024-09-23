package com.hummingbird.kr.starbuckslike.shipping.dto.out;

import com.hummingbird.kr.starbuckslike.shipping.vo.out.ShippingDefaultIDResponseVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@Getter
public class ShippingDefaultIDResponseDTO {
    private @Nullable Long id;

    public ShippingDefaultIDResponseVO toVO() {
        return new ShippingDefaultIDResponseVO(this.id);
    }
}
