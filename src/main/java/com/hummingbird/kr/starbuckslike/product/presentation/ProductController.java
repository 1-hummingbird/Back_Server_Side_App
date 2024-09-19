package com.hummingbird.kr.starbuckslike.product.presentation;


import com.hummingbird.kr.starbuckslike.common.entity.CommonResponseEntity;
import com.hummingbird.kr.starbuckslike.common.entity.CommonResponseMessage;
import com.hummingbird.kr.starbuckslike.product.application.ProductService;
import com.hummingbird.kr.starbuckslike.product.dto.out.*;
import com.hummingbird.kr.starbuckslike.product.infrastructure.condition.ProductCondition;
import com.hummingbird.kr.starbuckslike.product.vo.*;
import com.hummingbird.kr.starbuckslike.purchase.dto.in.AddPurchaseRequestDto;
import com.hummingbird.kr.starbuckslike.purchase.vo.in.AddPurchaseRequestVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    /**
     * 상품 리스트 단건 조회
     */
    @Operation(summary = "상품 리스트 단건 조회(이미지)", description = "상품 id로 상품리스트 이미지 단건 조회")
    @GetMapping("/list/image/{productId}")
    public CommonResponseEntity<ProductListImageResponseVo> findProductListImageResponseDtoByIdV1(
            @PathVariable("productId") Long productId){

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                productService.findProductListImageResponseDtoById(productId).toVo()
        );
    }

    @Operation(summary = "상품 리스트 단건 정보 조회(이름,가격 등)", description = "상품 id로 상품리스트 정보(이름,가격 등) 단건 조회")
    @GetMapping("/list/info/{productId}")
    public CommonResponseEntity<ProductListInfoResponseVo> findProductListInfoResponseDtoByIdV1(
            @PathVariable("productId") Long productId){
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                productService.findProductListInfoResponseDtoById(productId).toVo()
        );
    }
    /**
     * 상품 디테일
     */
    // 상품 디테일 상품 상품명 가격 등 조회
    @Operation(summary = "상품 디테일(상품명,가격,할인 등) 조회", description = "상품 id로 상품 디테일(상품명,가격,할인 등) 조회")
    @GetMapping("/info/{productId}")
    public CommonResponseEntity<ProductInfoResponseVo> findProductInfoByIdV1(
            @PathVariable("productId") Long productId){
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                productService.findProductInfoById(productId).toVo()
        );
    }
    // 상품 디테일 상세정보(에티터 html) 조회
    @Operation(summary = "상품 디테일 상세정보 조회", description = "상품 상세정보(에디터) 조회")
    @GetMapping("/detail/{productId}")
    public CommonResponseEntity<ProductDetailResponseVo> findProductDetailDtoByIdV1(
            @PathVariable("productId") Long productId){
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                productService.findProductDetailDtoById(productId).toVo()
        );
    }
    // 상품의 이미지 조회
    @Operation(summary = "상품 디테일 이미지 조회", description = "상품 id로 상품 이미지 조회")
    @GetMapping("/images/{productId}")
    public CommonResponseEntity<List<ProductImageResponseVo>> findProductImageDtoByIdV1(
            @PathVariable("productId") Long productId){
        // map 으로 Vo 변환
        List<ProductImageResponseVo> responseVoList =
                productService.findProductImageDtoById(productId)
                    .stream()
                    .map(ProductImageResponseDto::toVo)
                    .collect(Collectors.toList());

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                responseVoList
        );
    }
    // 상품 옵션 조회
    @Operation(summary = "상품 디테일 옵션 조회", description = "상품 id로 상품 옵션 조회")
    @GetMapping("/options/{productId}")
    public CommonResponseEntity<List<ProductOptionResponseVo>> findProductOptionDtoByIdV1(
            @PathVariable("productId") Long productId){

        List<ProductOptionResponseVo> responseVoList =
                productService.findProductOptionDtoById(productId)
                        .stream()
                        .map(ProductOptionResponseDto::toVo)
                        .collect(Collectors.toList());

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                responseVoList
        );
    }
    /**
     * 기획전에 해당하는 상품 리스트
     */
    // 기획전으로 상품 id 리스트 조회
    @Operation(summary = "기획전 상품 리스트", description = "기획전에 해당하는 상품 id 리스트 조회")
    @GetMapping("/list/exhibition/{exhibitionId}")
    public CommonResponseEntity<List<Long>> findProductIdListByExhibitionIdV1(
            @PathVariable("exhibitionId") Long exhibitionId){
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                productService.findProductIdListByExhibitionId(exhibitionId)
        );
    }

    /**
     *  메인 상품 리스트 필터링[카테고리,가격] , 정렬 조건 적용
     */
    // 몽땅 가져오는 코드
//    @Operation(summary = "상품 리스트 조회 [필터링, 정렬]",
//            description = "필터링[카테고리(상,하), 가격] 정렬[최신순,할인순,높은가격,낮은가격]")
//    @GetMapping("/list")
//    public CommonResponseEntity<Page<ProductListResponseVo>> searchProductListPageV1(
//            ProductCondition productCondition, Pageable pageable ){
//
//        Page<ProductListResponseDto> productPage = productService.searchProductListPageV1(productCondition, pageable);
//        // to Vo
//        Page<ProductListResponseVo> res = productPage.map(ProductListResponseDto::toVo);
//
//        return new CommonResponseEntity<>(
//                HttpStatus.OK,
//                CommonResponseMessage.SUCCESS.getMessage(),
//                res
//        );
//    }

    @Operation(summary = "상품 리스트 조회 [필터링, 정렬] ",
            description = "[Slice] 필터링[카테고리(상,하), 가격] 정렬[최신순,할인순,높은가격,낮은가격] 해당 상품들의 id만 가져옴")
    @GetMapping("/list")
    public CommonResponseEntity<Slice<Long>> searchProductIdsV1(
            ProductCondition productCondition, Pageable pageable ){

        Slice<Long> productIds =
                productService.searchProductIdsV1(productCondition, pageable);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                productIds
        );
    }

    /**
     *  상품 위시리스트 관련
     */
    @Operation(summary = "상품 위시리스트 활성,비활성", description = "토글방식으로 동작")
    @PostMapping("/wish")
    public CommonResponseEntity<Void> updateWishStatusV1(
            // todo 팀장님 나중에 productId만 @RequestBody로 쓰시고 memberUid는 시큐리티에서 뽑아요
            @RequestParam("memberUid") String memberUid,
            @RequestParam("productId") Long productId
    )
    {
        productService.updateWishStatus(memberUid , productId);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                null
        );
    }

    @Operation( security = @SecurityRequirement(name = "Bearer Auth"),
            summary = "상품 위시리스트 조회", description = "[Slice] 회원이 위시리스트한 상품만 조회")
    @GetMapping("/wish/list")
    public CommonResponseEntity<Slice<Long>> searchWishProductIdsV1(
                Pageable pageable, @RequestParam("memberUid") String memberUid ){

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                productService.searchWishProductIdsV1(pageable,memberUid)
        );
    }






}
