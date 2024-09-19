package com.hummingbird.kr.starbuckslike.shipping.dto.in;


import com.hummingbird.kr.starbuckslike.shipping.domain.ShippingAddress;
import com.hummingbird.kr.starbuckslike.shipping.vo.in.ShippingAddressRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShippingAddressRequestDto {

    private String userUuid;
    private String addressNickname;
    private String name;
    private String address;
    private String primaryPhone;
    private String secondaryPhone;
    private String memo;
    private Boolean defaultAddress;

    @Builder
    public ShippingAddressRequestDto(String userUuid,
                                     String addressNickname,
                                     String name, String address,
                                     String primaryPhone,
                                     String secondaryPhone,
                                     String memo,
                                     Boolean defaultAddress
    ) {
        this.userUuid = userUuid;
        this.addressNickname = addressNickname;
        this.name = name;
        this.address = address;
        this.primaryPhone = primaryPhone;
        this.secondaryPhone = secondaryPhone;
        this.memo = memo;
        this.defaultAddress = defaultAddress;
    }

    public ShippingAddress toEntity(){
        return ShippingAddress.builder()
                .address(address)
                .name(name)
                .addressNickname(addressNickname)
                .memo(memo)
                .defaultAddress(defaultAddress)
                .primaryPhone(primaryPhone)
                .secondaryPhone(secondaryPhone)
                .userUuid(userUuid)
                .build();
    }

    public static ShippingAddressRequestDto of(ShippingAddressRequestVo shippingAddressRequestVo){
        return ShippingAddressRequestDto.builder()
                .address(shippingAddressRequestVo.getAddress())
                .memo(shippingAddressRequestVo.getMemo())
                .defaultAddress(shippingAddressRequestVo.getDefaultAddress())
                .name(shippingAddressRequestVo.getName())
                .addressNickname(shippingAddressRequestVo.getAddressNickname())
                .primaryPhone(shippingAddressRequestVo.getPrimaryPhone())
                .secondaryPhone(shippingAddressRequestVo.getSecondaryPhone())
                .userUuid("userUuid")
                .build();
    }


}
