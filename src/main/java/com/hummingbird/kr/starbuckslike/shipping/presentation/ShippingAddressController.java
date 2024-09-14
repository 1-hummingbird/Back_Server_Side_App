package com.hummingbird.kr.starbuckslike.shipping.presentation;



import com.hummingbird.kr.starbuckslike.shipping.application.ShippingAddressService;
import com.hummingbird.kr.starbuckslike.shipping.dto.in.ShippingAddressRequestDto;
import com.hummingbird.kr.starbuckslike.shipping.vo.in.ShippingAddressRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor   //생성자를 만들어주는 역할.
@RestController
@RequestMapping("/api/v1/shipping-address")
public class ShippingAddressController {

    private final ShippingAddressService shippingAddressService;
    @PostMapping
    public void addShippingAddress(@RequestBody ShippingAddressRequestVo shippingAddressRequestVo){
        shippingAddressService.addShippingAddress(
                ShippingAddressRequestDto.builder()
                        .defaultAddress(shippingAddressRequestVo.getDefaultAddress())
                        .address(shippingAddressRequestVo.getAddress())
                        .name(shippingAddressRequestVo.getName())
                        .memo(shippingAddressRequestVo.getMemo())
                        .addressNickname(shippingAddressRequestVo.getAddressNickname())
                        .primaryPhone(shippingAddressRequestVo.getPrimaryPhone())
                        .secondaryPhone(shippingAddressRequestVo.getSecondaryPhone())
                        .userUuid("Uuid")
                        .build()
        );
    }
}
