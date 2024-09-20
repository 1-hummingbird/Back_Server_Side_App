package com.hummingbird.kr.starbuckslike.shipping.dto.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Builder;
import org.springframework.lang.Nullable;

@Getter
@Builder
@AllArgsConstructor
public class ShippingAddressUpdateRequestDTO {
    private Long shippingAddressID;
    private @Nullable String addressNickname;
    private @Nullable String name;
    private @Nullable String address;
    private @Nullable String phone;
    private @Nullable String memo;
    private String memberUID;
}
