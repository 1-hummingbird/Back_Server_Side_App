package com.hummingbird.kr.starbuckslike.shipping.presentation;

import com.hummingbird.kr.starbuckslike.auth.domain.AuthUserDetail;
import com.hummingbird.kr.starbuckslike.common.entity.BaseResponse;
import com.hummingbird.kr.starbuckslike.common.entity.BaseResponseStatus;
import com.hummingbird.kr.starbuckslike.shipping.application.*;
import com.hummingbird.kr.starbuckslike.shipping.vo.in.*;
import com.hummingbird.kr.starbuckslike.shipping.vo.out.*;
import com.hummingbird.kr.starbuckslike.shipping.dto.in.*;
import com.hummingbird.kr.starbuckslike.shipping.dto.out.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/v1/shipping")
public class ShippingAddressController {

    private final ShippingAddressService shippingAddressService;

    @Autowired
    public ShippingAddressController(ShippingAddressService shippingAddressService) {
        this.shippingAddressService = shippingAddressService;
    }

    @PostMapping("/add")
    public ShippingAddressAddResponseVO add(@RequestBody ShippingAddressAddRequestVO requestVO, @AuthenticationPrincipal AuthUserDetail authUserDetail) {
        ShippingAddressAddRequestDTO requestDTO = requestVO.toDTO();
        ShippingAddressAddResponseDTO responseDTO = shippingAddressService.add(requestDTO);
        return new ShippingAddressAddResponseVO(responseDTO);
    }

    @PostMapping("/update")
    public ShippingAddressUpdateResponseVO update(@RequestBody ShippingAddressUpdateRequestVO requestVO, @AuthenticationPrincipal AuthUserDetail authUserDetail) {
        ShippingAddressUpdateRequestDTO requestDTO = requestVO.toDTO();
        ShippingAddressUpdateResponseDTO responseDTO = shippingAddressService.update(requestDTO);
        return new ShippingAddressUpdateResponseVO(responseDTO);
    }

    @PostMapping("/delete")
    public ShippingAddressDeleteResponseVO delete(@RequestBody ShippingAddressDeleteRequestVO requestVO, @AuthenticationPrincipal AuthUserDetail authUserDetail) {
        ShippingAddressDeleteRequestDTO requestDTO = requestVO.toDTO();
        ShippingAddressDeleteResponseDTO responseDTO = shippingAddressService.delete(requestDTO);
        return new ShippingAddressDeleteResponseVO(responseDTO);
    }

    @PostMapping("/list")
    public ShippingAddressListResponseVO list(@AuthenticationPrincipal AuthUserDetail authUserDetail) {
        ShippingAddressListResponseDTO responseDTO = shippingAddressService.list(authUserDetail.getUsername());
        return new ShippingAddressListResponseVO(responseDTO);
    }

    @PostMapping("/set-default")
    public ShippingAddressSetDefaultResponseVO setDefault(@RequestBody ShippingAddressSetDefaultRequestVO requestVO, @AuthenticationPrincipal AuthUserDetail authUserDetail) {
        ShippingAddressSetDefaultRequestDTO requestDTO = requestVO.toDTO();
        ShippingAddressSetDefaultResponseDTO responseDTO = shippingAddressService.setDefault(requestDTO);
        return new ShippingAddressSetDefaultResponseVO(responseDTO);
    }

    
}
