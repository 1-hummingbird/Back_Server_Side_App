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
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController
@Slf4j
@RequestMapping("/api/v1/shipping")
public class ShippingAddressController {

    private final ShippingService shippingService;

    @Autowired
    public ShippingAddressController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @PostMapping("/add")
    public BaseResponse<Void> add(@RequestBody ShippingAddressAddRequestVO requestVO, @AuthenticationPrincipal AuthUserDetail authUserDetail) {
        ShippingAddressAddRequestDTO requestDTO = requestVO.toDTO(authUserDetail.getUsername());
        shippingService.add(requestDTO);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @PostMapping("/update")
    public BaseResponse<Void> update(@RequestBody ShippingAddressUpdateRequestVO requestVO, @AuthenticationPrincipal AuthUserDetail authUserDetail) {
        ShippingAddressUpdateRequestDTO requestDTO = requestVO.toDTO(authUserDetail.getUsername());
        shippingService.update(requestDTO);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @PostMapping("/delete")
    public BaseResponse<Void> delete(@RequestBody ShippingAddressDeleteRequestVO requestVO, @AuthenticationPrincipal AuthUserDetail authUserDetail) {
        ShippingAddressDeleteRequestDTO requestDTO = requestVO.toDTO(authUserDetail.getUsername());
        shippingService.delete(requestDTO);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @PostMapping("/list")
    public BaseResponse<ShippingAddressListResponseVO> list(@AuthenticationPrincipal AuthUserDetail authUserDetail) {
        ShippingAddressListResponseDTO responseDTO = shippingService.list(authUserDetail.getUsername());
        return new BaseResponse<>(new ShippingAddressListResponseVO(responseDTO));
    }

    @PostMapping("/set-default")
    public BaseResponse<Void> setDefault(@RequestBody ShippingAddressSetDefaultRequestVO requestVO, @AuthenticationPrincipal AuthUserDetail authUserDetail) {
        ShippingAddressSetDefaultRequestDTO requestDTO = requestVO.toDTO(authUserDetail.getUsername());
        shippingService.setDefault(requestDTO);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    
}
