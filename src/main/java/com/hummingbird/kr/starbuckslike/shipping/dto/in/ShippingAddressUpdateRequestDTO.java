package com.hummingbird.kr.starbuckslike.shipping.dto.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Builder;
import org.springframework.lang.Nullable;
import com.hummingbird.kr.starbuckslike.shipping.domain.ShippingAddress;
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

    public ShippingAddress toEntity(ShippingAddress oldEntity) {
        return ShippingAddress.builder()
            .id(oldEntity.getId())
            .addressNickname(this.addressNickname != null ? this.addressNickname : oldEntity.getAddressNickname())
            .name(this.name != null ? this.name : oldEntity.getName())
            .address(this.address != null ? this.address : oldEntity.getAddress())
            .Phone(this.phone != null ? this.phone : oldEntity.getPhone())
            .memo(this.memo != null ? this.memo : oldEntity.getMemo())
            .memberUID(oldEntity.getMemberUID())
            .build();
    }
}
