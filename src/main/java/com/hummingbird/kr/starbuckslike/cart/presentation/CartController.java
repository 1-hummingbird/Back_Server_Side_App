package com.hummingbird.kr.starbuckslike.cart.presentation;

import com.hummingbird.kr.starbuckslike.cart.application.CartService;
import com.hummingbird.kr.starbuckslike.cart.dto.in.*;
import com.hummingbird.kr.starbuckslike.cart.vo.in.*;
import com.hummingbird.kr.starbuckslike.cart.vo.out.*;
import com.hummingbird.kr.starbuckslike.common.entity.BaseResponse;
import com.hummingbird.kr.starbuckslike.common.entity.BaseResponseStatus;
import com.hummingbird.kr.starbuckslike.auth.domain.AuthUserDetail;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;


@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;

    // 장바구니 아이템 추가 V1
    @Operation(summary = "장바구니 추가", description = "처음 담으면 insert , 기존 상품은 수량 update", tags = "Cart", security = @SecurityRequirement(name = "Bearer Auth"))
    @PostMapping("")
    public BaseResponse<Void> addCartItemV1(@RequestBody RequestAddCartItemVo requestAddCartItemVo, @AuthenticationPrincipal AuthUserDetail authUserDetail){

        cartService.addCartItemV2(RequestAddCartItemDto.toDto(requestAddCartItemVo, authUserDetail.getUsername()));
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    // 장바구니 아이템 (증가 또는 감소) V1
    @Operation(summary = "장바구니 아이템 수량 조정", description = "증가 또는 감소", tags = "Cart", security = @SecurityRequirement(name = "Bearer Auth"))
    @PostMapping("/adjust-quantity")
    public BaseResponse<Void> adjustCartItemQuantityV1(
            @Parameter(name = "CartAdjustType",
                    description = "cartAdjustType : INCREASE(증가), DECREASE(감소)", required = true)
            @RequestBody RequestAdjustCartItemVo requestAdjustCartItemVo){

        RequestAdjustCartItemDto requestAdjustCartItemDto =
                                            RequestAdjustCartItemDto.builder()
                                                    .cartId(requestAdjustCartItemVo.getCartId())
                                                    .cartAdjustType(requestAdjustCartItemVo.getCartAdjustType())
                                                    .build();
        cartService.adjustCartItemQuantity(requestAdjustCartItemDto);

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    // 장바구니 단건 삭제 V1
    @Operation(summary = "장바구니 단건 삭제", description = "장바구니 id로 장바구니 단건 삭제", tags = "Cart", security = @SecurityRequirement(name = "Bearer Auth"))
    @PostMapping("/delete")
        public BaseResponse<Void>removeCartItemV1(@RequestBody RequestRemoveCartItemVo requestRemoveCartItemVo, @AuthenticationPrincipal AuthUserDetail authUserDetail){
        cartService.removeCartItem(RequestRemoveCartItemDto.toDto(requestRemoveCartItemVo, authUserDetail.getUsername()));

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }


    // 장바구니 전체 삭제 V1
    @Operation(summary = "장바구니 전체 삭제", description = "회원 uuid로 장바구니 전체 삭제", tags = "Cart", security = @SecurityRequirement(name = "Bearer Auth"))
    @PostMapping("/delete-all")
    public BaseResponse<Void>removeAllCartItemsByUserUidV1(@AuthenticationPrincipal AuthUserDetail authUserDetail) {
        log.info(authUserDetail.getUsername());
        cartService.removeAllCartItemsByMemberUID(authUserDetail.getUsername());

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    // 장바구니 단 건 선택
    @Operation(summary = "장바구니 단건 선택 (토글)", description = "장바구니 id로 장바구니 단건 선택", tags = "Cart", security = @SecurityRequirement(name = "Bearer Auth"))
    @PostMapping("/select")
    public BaseResponse<Void>selectCartItemV1(@RequestBody RequestSelectCartItemVo requestSelectCartItemVo, @AuthenticationPrincipal AuthUserDetail authUserDetail){
        cartService.selectCartItem(RequestSelectCartItemDto.toDto(requestSelectCartItemVo, authUserDetail.getUsername()));
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    // 장바구니 전체 선택(활성,비활성)
    @Operation(summary = "장바구니 전체 선택", description = "장바구니 id 리스로 장바구니 전체 선택", tags = "Cart", security = @SecurityRequirement(name = "Bearer Auth"))
    @PostMapping("/select-all")
    public BaseResponse<Void>selectCartItemsV1(
            @RequestBody RequestCartItemSelectAllVo requestCartItemSelectAllVo){
        cartService.selectAllCartItems(RequestCartItemSelectAllDto.toDto(requestCartItemSelectAllVo));
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }


    // 장바구니 ID 리스트 조회
    @Operation(summary = "장바구니 ID 리스트 조회", description = "", tags = "Cart", security = @SecurityRequirement(name = "Bearer Auth"))
    @PostMapping("/items/member")
    public BaseResponse<ResponseFindAllCartVo> findAllCartIdByUserUidV1(@AuthenticationPrincipal AuthUserDetail authUserDetail){
        return new BaseResponse<>(
                cartService.findAllCartIdByMemberUID(authUserDetail.getUsername()).toVo()
        );
    }


    // 장바구니 옵션상품의 대표상품 이미지 조회
    @Operation(summary = "장바구니 이미지 조회", description = "장바구니 옵션상품의 대표상품 이미지 조회", tags = "Cart", security = @SecurityRequirement(name = "Bearer Auth"))
    @GetMapping("/item/image/{cartId}")
    public BaseResponse<ResponseCartItemImageVo> findCartMainImageDtoByIdV1(
            @PathVariable("cartId") Long cartId){
        return new BaseResponse<>(
                cartService.findCartMainImageDtoById(cartId).toVo()
        );
    }


    // 장바구니 옵션상품 정보(옵션가격,수량,옵션명 등등) 조회
    @Operation(summary = "장바구니 옵션정보 조회", description = "옵션상품 정보(옵션가격,수량,옵션명 등등) 조회", tags = "Cart", security = @SecurityRequirement(name = "Bearer Auth"))
    @PostMapping("/item/info")
    public BaseResponse<ResponseCartItemVo> findCartItemDtoByIdV1(@RequestBody RequestCartInfoVo vo){
        return new BaseResponse<>(
                cartService.findCartItemDtoById(RequestCartInfoDto.from(vo)).toVo()
        );
    }


}
