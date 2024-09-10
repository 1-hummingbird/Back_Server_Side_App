package com.hummingbird.kr.starbuckslike.cart.presentation;

import com.hummingbird.kr.starbuckslike.cart.application.CartService;
import com.hummingbird.kr.starbuckslike.cart.dto.in.RequestAddCartItemDto;
import com.hummingbird.kr.starbuckslike.cart.dto.in.RequestAdjustCartItemDto;
import com.hummingbird.kr.starbuckslike.cart.vo.RequestAddCartItemVo;
import com.hummingbird.kr.starbuckslike.cart.vo.RequestAdjustCartItemVo;
import com.hummingbird.kr.starbuckslike.cart.vo.ResponseCartItemImageVo;
import com.hummingbird.kr.starbuckslike.cart.vo.ResponseCartItemVo;
import com.hummingbird.kr.starbuckslike.common.entity.CommonResponseEntity;
import com.hummingbird.kr.starbuckslike.common.entity.CommonResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
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
    @Operation(summary = "장바구니 추가", description = "처음 담으면 insert , 기존 상품은 수량 update")
    @PostMapping("")
    public CommonResponseEntity<Void> addCartItemV1(@RequestBody RequestAddCartItemVo requestAddCartItemVo){
        RequestAddCartItemDto requestAddCartItemDto =
                                                        RequestAddCartItemDto.builder()
                                                            .memberUID(requestAddCartItemVo.getMemberUID())
                                                            .productId(requestAddCartItemVo.getProductId())
                                                            .optionId(requestAddCartItemVo.getOptionId())
                                                            .qty(requestAddCartItemVo.getQty())
                                                            .inputData(requestAddCartItemVo.getInputData())
                                                            .build();

        cartService.addCartItem(requestAddCartItemDto);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                null
        );
    }

    // 장바구니 아이템 (증가 또는 감소) V1
    @Operation(summary = "장바구니 아이템 수량 조정", description = "증가 또는 감소")
    @PostMapping("/adjust-quantity")
    public CommonResponseEntity<Void> adjustCartItemQuantityV1(
            @Parameter(name = "CartAdjustType",
                    description = "cartAdjustType : INCREASE(증가), DECREASE(감소)", required = true)
            @RequestBody RequestAdjustCartItemVo requestAdjustCartItemVo){

        RequestAdjustCartItemDto requestAdjustCartItemDto =
                                            RequestAdjustCartItemDto.builder()
                                                    .cartId(requestAdjustCartItemVo.getCartId())
                                                    .cartAdjustType(requestAdjustCartItemVo.getCartAdjustType())
                                                    .build();
        cartService.adjustCartItemQuantity(requestAdjustCartItemDto);

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                null
        );
    }

    // 장바구니 단건 삭제 V1
    @Operation(summary = "장바구니 단건 삭제", description = "장바구니 id로 장바구니 단건 삭제")
    @PostMapping("/delete/{cartId}")
    public CommonResponseEntity<Void>removeCartItemV1(@PathVariable("cartId") Long cartId){
        cartService.removeCartItem(cartId);

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                null
        );
    }


    // 장바구니 전체 삭제 V1
    @Operation(summary = "장바구니 전체 삭제", description = "회원 uuid로 장바구니 전체 삭제")
    @PostMapping("/delete-all/{userUid}")
    public CommonResponseEntity<Void>removeAllCartItemsByUserUidV1(@PathVariable("userUid") String userUid) {
        log.info(userUid);
        cartService.removeAllCartItemsByUserUid(userUid);

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                null
        );
    }

    // 장바구니 단 건 선택
    @Operation(summary = "장바구니 단건 선택 (토글)", description = "장바구니 id로 장바구니 단건 선택")
    @PostMapping("/select/{cartId}")
    public CommonResponseEntity<Void>selectCartItemV1(@PathVariable("cartId") Long cartId){
        cartService.selectCartItem(cartId);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                null
        );
    }

    // 장바구니 전체 선택(활성,비활성)
    @Operation(summary = "장바구니 전체 선택", description = "장바구니 id 리스로 장바구니 전체 선택")
    @PostMapping("/select-all")
    public CommonResponseEntity<Void>selectCartItemsV1(
            @RequestBody List<Long> cartIds){
        cartService.selectAllCartItems(cartIds);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                null
        );
    }


    // 장바구니 ID 리스트 조회
    @Operation(summary = "장바구니 ID 리스트 조회", description = "")
    @GetMapping("/items/id/{userUid}")
    public CommonResponseEntity<List<Long>> findAllCartIdByUserUidV1(@PathVariable("userUid") String userUid){
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                cartService.findAllCartIdByUserUid(userUid)
        );
    }


    // 장바구니 옵션상품의 대표상품 이미지 조회
    @Operation(summary = "장바구니 이미지 조회", description = "장바구니 옵션상품의 대표상품 이미지 조회")
    @GetMapping("/item/image/{cartId}")
    public CommonResponseEntity<ResponseCartItemImageVo> findCartMainImageDtoByIdV1(
            @PathVariable("cartId") Long cartId){
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                cartService.findCartMainImageDtoById(cartId).toVo()
        );
    }


    // 장바구니 옵션상품 정보(옵션가격,수량,옵션명 등등) 조회
    @Operation(summary = "장바구니 옵션정보 조회", description = "옵션상품 정보(옵션가격,수량,옵션명 등등) 조회")
    @GetMapping("/item/info/{cartId}")
    public CommonResponseEntity<ResponseCartItemVo> findCartItemDtoByIdV1(@PathVariable("cartId") Long cartId){
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                cartService.findCartItemDtoById(cartId).toVo()
        );
    }


}
