package com.hummingbird.kr.starbuckslike.cart.presentation;

import com.hummingbird.kr.starbuckslike.cart.application.CartService;
import com.hummingbird.kr.starbuckslike.cart.dto.*;
import com.hummingbird.kr.starbuckslike.common.entity.CommonResponseEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// todo 팀장님 spring security 작업되면 수정

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;

    // 장바구니 아이템 추가 V1
    @PostMapping("")
    public CommonResponseEntity<Void> addCartItemV1(@RequestBody RequestAddCartItemDto requestAddCartItemDto){
        cartService.addCartItem(requestAddCartItemDto);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "장바구니 아이템 추가 성공",
                null
        );
    }

    // 장바구니 아이템 (증가 또는 감소) V1
    @PostMapping("/adjust-quantity")
    public CommonResponseEntity<Void> adjustCartItemQuantityV1(
            @RequestBody RequestAdjustCartItemDto requestAdjustCartItemDto){
        cartService.adjustCartItemQuantity(requestAdjustCartItemDto);

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "장바구니 수량 조정 성공",
                null
        );
    }

    // 장바구니 단건 삭제 V1
    @PostMapping("/delete/{cartId}")
    public CommonResponseEntity<Void>removeCartItemV1(@PathVariable("cartId") Long cartId){
        cartService.removeCartItem(cartId);

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "장바구니 삭제 성공",
                null
        );
    }


    // 장바구니 전체 삭제 V1
    @PostMapping("/delete-all/{userUid}")
    public CommonResponseEntity<Void>removeAllCartItemsByUserUidV1(@PathVariable("userUid") String userUid) {
        log.info(userUid);
        cartService.removeAllCartItemsByUserUid(userUid);

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "장바구니 전체 삭제 성공",
                null
        );
    }

    // 장바구니 단 건 선택
    @PostMapping("/select/{cartId}")
    public CommonResponseEntity<Void>selectCartItemV1(@PathVariable("cartId") Long cartId){
        cartService.selectCartItem(cartId);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "장바구니 선택 성공",
                null
        );
    }

    // 장바구니 전체 선택(활성,비활성)
    @PostMapping("/select-all")
    public CommonResponseEntity<Void>selectCartItemsV1(
            @RequestBody List<Long> cartIds){
        cartService.selectAllCartItems(cartIds);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "장바구니 전체 선택 성공",
                null
        );
    }


    // 장바구니 ID 리스트 조회
    @GetMapping("/items/id/{userUid}")
    public CommonResponseEntity<List<Long>> findAllCartIdByUserUidV1(@PathVariable("userUid") String userUid){
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "장바구니 id 리스트 조회 성공",
                cartService.findAllCartIdByUserUid(userUid) // todo : VO로 변환 필요
        );
    }



    // 장바구니 옵션상품의 대표상품 이미지 조회
    @GetMapping("/item/image/{cartId}")
    public CommonResponseEntity<ResponseCartItemImageDto> findCartMainImageDtoByIdV1(
            @PathVariable("cartId") Long cartId){
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "장바구니 이미지 성공",
                cartService.findCartMainImageDtoById(cartId) // todo : VO로 변환 필요
        );
    }


    // 장바구니 옵션상품 정보(옵션가격,수량,옵션명 등등) 조회
    @GetMapping("/item/info/{cartId}")
    public CommonResponseEntity<ResponseCartItemDto> findCartItemDtoByIdV1(@PathVariable("cartId") Long cartId){
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "장바구니 옵션 정보 성공",
                cartService.findCartItemDtoById(cartId) // todo : VO로 변환 필요
        );
    }


}
