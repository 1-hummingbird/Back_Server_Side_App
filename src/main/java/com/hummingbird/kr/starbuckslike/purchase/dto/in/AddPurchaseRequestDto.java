package com.hummingbird.kr.starbuckslike.purchase.dto.in;

import com.hummingbird.kr.starbuckslike.purchase.domain.Purchase;
import com.hummingbird.kr.starbuckslike.purchase.domain.PurchaseProduct;
import com.hummingbird.kr.starbuckslike.purchase.domain.PurchaseStatus;
import com.hummingbird.kr.starbuckslike.purchase.vo.in.AddPurchaseRequestVo;
import com.hummingbird.kr.starbuckslike.review.dto.in.AddReviewImageRequestDto;
import com.hummingbird.kr.starbuckslike.review.dto.in.AddReviewRequestDto;
import com.hummingbird.kr.starbuckslike.review.vo.in.AddReviewRequestVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 주문을 위한 DTO (장바구니, 상품디테일에서 각각 주문이 가능하기에 주문상품 필드는 List 로 작성)
 * @author 허정현
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddPurchaseRequestDto {
    private Long totalPrice; // 총 구매금액
    private Long totalDiscount; // 총 할인금액

    private String address; // 주소
    private String primaryPhone;
    private String secondaryPhone;
    private String userName;
    private String memberUID;
    private String memo; // 요청사항
    private List<AddPurchaseItemRequestDto> purchaseProducts; // 주문 상품옵션

    // 주문 엔티티 생성
    public Purchase toPurchase(String purchaseCode) {
        return Purchase.builder()
                .code(purchaseCode)
                .totalPrice(totalPrice)
                .totalDiscount(totalDiscount)
                .address(address)
                .primaryPhone(primaryPhone)
                .secondaryPhone(secondaryPhone)
                .userName(userName)
                .memberUID(memberUID)
                .memo(memo)
                .isDelete(false)
                .build();
    }
    // 주문 상품 엔티티들 생성
    public List<PurchaseProduct> toPurchaseProduct(Purchase purchase) {
        // 각각의 주문 상품을 PurchaseProduct 로 변환
        return purchaseProducts.stream()
                                        .map(itemDto -> PurchaseProduct.builder()
                                                            .purchase(purchase) // 어떤 주문인지 매핑
                                                            .qty(itemDto.getQty())
                                                            .price(itemDto.getPrice())
                                                            .discountPrice(itemDto.getDiscountPrice())
                                                            .inputData(itemDto.getInputData())
                                                            .productId(itemDto.getProductId())
                                                            .productName(itemDto.getProductName())
                                                            .optionId(itemDto.getOptionId())
                                                            .optionName(itemDto.getOptionName())
                                                            .purchaseStatus(PurchaseStatus.PENDING)
                                                            .build())
                                        .collect(Collectors.toList());
    }
    // vo -> dto
    public static AddPurchaseRequestDto of(AddPurchaseRequestVo vo, String memberUid) {
        return AddPurchaseRequestDto.builder()
                .totalDiscount(vo.getTotalPrice())
                .totalDiscount(vo.getTotalDiscount())
                .address(vo.getAddress())
                .primaryPhone(vo.getPrimaryPhone())
                .secondaryPhone(vo.getSecondaryPhone())
                .userName(vo.getUserName())
                .memberUID(memberUid)
                .memo(vo.getMemo())
                .purchaseProducts(
                        vo.getPurchaseProducts()
                                .stream()
                                .filter(Objects::nonNull)
                                .map(itemVo -> AddPurchaseItemRequestDto.builder()
                                        .productId(itemVo.getProductId())
                                        .productName(itemVo.getProductName())
                                        .optionId(itemVo.getOptionId())
                                        .optionName(itemVo.getOptionName())
                                        .qty(itemVo.getQty())
                                        .price(itemVo.getPrice())
                                        .discountPrice(itemVo.getDiscountPrice())
                                        .inputData(itemVo.getInputData())
                                        .build()
                                ).collect(Collectors.toList())
                )
                .build();
    }


}
