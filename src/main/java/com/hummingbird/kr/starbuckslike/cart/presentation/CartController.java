package com.hummingbird.kr.starbuckslike.cart.presentation;

import com.hummingbird.kr.starbuckslike.cart.application.CartService;
import com.hummingbird.kr.starbuckslike.cart.dto.RequestAddCartItemDto;
import com.hummingbird.kr.starbuckslike.common.entity.CommonResponseEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CartController {

    private final CartService cartService;

    // 장바구니 아이템 추가
    @PostMapping("/v1/cart")
    public CommonResponseEntity<Void> addCartItemV1(@RequestBody RequestAddCartItemDto requestAddCartItemDto){
        cartService.addCartItem(requestAddCartItemDto);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "장바구니 아이템 추가 성공",
                null
        );
    }

    //@PostMapping("/v1/") //removeCartItem






}
