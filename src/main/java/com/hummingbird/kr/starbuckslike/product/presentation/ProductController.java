package com.hummingbird.kr.starbuckslike.product.presentation;


import com.hummingbird.kr.starbuckslike.auth.domain.AuthUserDetail;
import com.hummingbird.kr.starbuckslike.common.entity.BaseResponse;
import com.hummingbird.kr.starbuckslike.common.entity.BaseResponseStatus;
import com.hummingbird.kr.starbuckslike.common.utils.CursorPage;
import com.hummingbird.kr.starbuckslike.product.application.ProductService;
import com.hummingbird.kr.starbuckslike.product.dto.out.*;
import com.hummingbird.kr.starbuckslike.product.infrastructure.condition.ProductCondition;
import com.hummingbird.kr.starbuckslike.product.vo.out.*;
import com.hummingbird.kr.starbuckslike.redis.service.RedisService;
import com.hummingbird.kr.starbuckslike.redis.vo.out.RecentSearchResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;
    private final RedisService redisService; // 최근검색어 캐싱
    /**
     * 상품 리스트 단건 조회
     */
    @Operation(summary = "상품 리스트 단건 조회(이미지)", description = "상품 id로 상품리스트 이미지 단건 조회", tags = {"상품"})
    @GetMapping("/list/image/{productId}")
    public BaseResponse<ProductListImageResponseVo> findProductListImageResponseDtoByIdV1(
            @PathVariable("productId") Long productId){

        return new BaseResponse<>(
                productService.findProductListImageResponseDtoById(productId).toVo()
        );
    }

    @Operation(summary = "상품 리스트 단건 정보 조회(이름,가격 등)", description = "상품 id로 상품리스트 정보(이름,가격 등) 단건 조회", tags = {"상품"})
    @GetMapping("/list/info/{productId}")
    public BaseResponse<ProductListInfoResponseVo> findProductListInfoResponseDtoByIdV1(
            @PathVariable("productId") Long productId){
        return new BaseResponse<>(
                productService.findProductListInfoResponseDtoById(productId).toVo()
        );
    }
    /**
     * 상품 디테일
     */
    // 상품 디테일 상품 상품명 가격 등 조회
    @Operation(summary = "상품 디테일(상품명,가격,할인 등) 조회", description = "상품 id로 상품 디테일(상품명,가격,할인 등) 조회", tags = {"상품"})
    @GetMapping("/info/{productId}")
    public BaseResponse<ProductInfoResponseVo> findProductInfoByIdV2(
            @PathVariable("productId") Long productId , @AuthenticationPrincipal AuthUserDetail authUserDetail){
        String memberUid = (authUserDetail != null) ? authUserDetail.getUuid() : "";
        //log.info("memberUid : "+memberUid);
        return new BaseResponse<>(
                productService.findProductInfoByIdV2(productId, memberUid).toVo()
        );
    }

    // 상품 디테일 상세정보(에티터 html) 조회
    @Operation(summary = "상품 디테일 상세정보 조회", description = "상품 상세정보(에디터) 조회", tags = {"상품"})
    @GetMapping("/detail/{productId}")
    public BaseResponse<ProductDetailResponseVo> findProductDetailDtoByIdV1(
            @PathVariable("productId") Long productId){
        return new BaseResponse<>(
                productService.findProductDetailDtoById(productId).toVo()
        );
    }
    // 상품의 이미지 조회
    @Operation(summary = "상품 디테일 이미지 조회", description = "상품 id로 상품 이미지 조회", tags = {"상품"})
    @GetMapping("/images/{productId}")
    public BaseResponse<List<ProductImageResponseVo>> findProductImageDtoByIdV1(
            @PathVariable("productId") Long productId){
        // map 으로 Vo 변환
        List<ProductImageResponseVo> responseVoList =
                productService.findProductImageDtoById(productId)
                    .stream()
                    .map(ProductImageResponseDto::toVo)
                    .collect(Collectors.toList());

        return new BaseResponse<>(
                responseVoList
        );
    }
    // 상품 옵션 조회
    @Operation(summary = "상품 디테일 옵션 조회", description = "상품 id로 상품 옵션 조회", tags = {"상품"})
    @GetMapping("/options/{productId}")
    public BaseResponse<List<ProductOptionResponseVo>> findProductOptionDtoByIdV1(
            @PathVariable("productId") Long productId){

        List<ProductOptionResponseVo> responseVoList =
                productService.findProductOptionDtoById(productId)
                        .stream()
                        .map(ProductOptionResponseDto::toVo)
                        .collect(Collectors.toList());

        return new BaseResponse<>(
                responseVoList
        );
    }
    @Operation(summary = "상품 디테일 상품 wish 여부", description = "상품 id, 회원 uuid 로 상품 wish 여부 조회", tags = {"상품"})
    @GetMapping("/detail/wish/{productId}")
    public BaseResponse<ProductIsWishedResponseVo> findProductIsWishedResponseDtoByIdV1(
            @PathVariable("productId") Long productId , @AuthenticationPrincipal AuthUserDetail authUserDetail){
        String memberUid = (authUserDetail != null) ? authUserDetail.getUuid() : "";
        return new BaseResponse<>(
                productService.findProductIsWishedResponseDtoById(productId, memberUid).toVo()
        );
    }
    /**
     * 기획전에 해당하는 상품 리스트
     */
    // 기획전으로 상품 id 리스트 조회
    @Operation(summary = "기획전 상품 리스트", description = "기획전에 해당하는 상품 id 리스트 조회", tags = {"기획전"})
    @GetMapping("/list/exhibition/{exhibitionId}")
    public BaseResponse<List<Long>> findProductIdListByExhibitionIdV1(
            @PathVariable("exhibitionId") Long exhibitionId){
        return new BaseResponse<>(
                productService.findProductIdListByExhibitionId(exhibitionId)
        );
    }
    /**
     *  메인 상품 리스트 필터링[카테고리,가격] , 정렬 조건 적용
     */

    @Operation(summary = "상품 리스트 조회 [필터링, 정렬] ",
            description = "[Slice] 필터링[카테고리(상,하), 가격] 정렬[최신순,할인순,높은가격,낮은가격] 해당 상품들의 id만 가져옴", tags = {"상품"})
    @GetMapping("/list")
    public BaseResponse<Slice<Long>> searchProductIdsV1(
            ProductCondition productCondition, Pageable pageable ){

        Slice<Long> productIds =
                productService.searchProductIdsV1(productCondition, pageable);
        return new BaseResponse<>(
                productIds
        );
    }
//    @Operation(summary = "상품 리스트 조회 [필터링, 정렬] Cursor",
//            description = "필터링[카테고리(상,하), 가격] 정렬[최신순,할인순,높은가격,낮은가격] 해당 상품들의 id만 가져옴", tags = {"상품"})
//    @GetMapping("/list2")
//    public BaseResponse<CursorPage<Long>> searchProductIdsCursorBaseV1(
//            ProductCondition productCondition,
//            @RequestParam( value = "lastId", required = false ) Long lastId,
//            @RequestParam( value = "pageSize", required = false ) Integer pageSize,
//            @RequestParam( value = "page", required = false ) Integer page
//    ){
//        return new BaseResponse<>(
//                productService.searchProductIdsCursorBase(productCondition, lastId, pageSize, page)
//        );
//    }

    /**
     *  상품 위시리스트 관련
     */
    @Operation(summary = "상품 위시리스트 활성,비활성", description = "토글방식으로 동작", tags = {"위시리스트"})
    @PostMapping("/wish")
    public BaseResponse<Void> updateWishStatusV1(
            @AuthenticationPrincipal AuthUserDetail authUserDetail,
            @RequestParam("productId") Long productId
    )
    {
        productService.updateWishStatus(authUserDetail.getUsername(), productId);
        return new BaseResponse<>(
                null
        );
    }

    @Operation( security = @SecurityRequirement(name = "Bearer Auth"),
            summary = "상품 위시리스트 조회", description = "[Slice] 회원이 위시리스트한 상품만 조회", tags = {"위시리스트"})
    @PostMapping("/wish/list")
    public BaseResponse<Slice<Long>> searchWishProductIdsV1(
                Pageable pageable, @AuthenticationPrincipal AuthUserDetail authUserDetail){

        return new BaseResponse<>(
                productService.searchWishProductIdsV1(pageable,authUserDetail.getUsername())
        );
    }

    /**
     * 상품 최근검색어
     */
    @Operation( security = @SecurityRequirement(name = "Bearer Auth"),
                summary = "최근 검색어 등록", description = "최근 검색어 등록", tags = {"최근검색어"})
    @PostMapping("/search/word")
    public BaseResponse<Void> addSearchWordV1(
            @AuthenticationPrincipal AuthUserDetail authUserDetail ,@RequestBody String searchWord ){
        redisService.addSearchWord(authUserDetail.getLoginId(), searchWord);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }
    @Operation( security = @SecurityRequirement(name = "Bearer Auth"),
            summary = "최근 검색어 조회", description = "회원의 최근 검색어 조회", tags = {"최근검색어"})
    @PostMapping("/search/list")
    public BaseResponse<RecentSearchResponseVo> getRecentSearchV1(
            @AuthenticationPrincipal AuthUserDetail authUserDetail ){
        log.info(authUserDetail.getLoginId());
        return new BaseResponse<>(
                redisService.getRecentSearchDto(authUserDetail.getLoginId()).toVo()
        );
    }
    @Operation( security = @SecurityRequirement(name = "Bearer Auth"),
            summary = "최근 검색어 개별 삭제", description = "회원의 최근 검색어 개별 삭제", tags = {"최근검색어"})
    @PostMapping("/search/word/delete")
    public BaseResponse<Void> deleteSearchWordV1(
            @AuthenticationPrincipal AuthUserDetail authUserDetail ,@RequestBody String searchWord ){
        redisService.deleteSearchWord(authUserDetail.getLoginId(), searchWord);
        return new BaseResponse<>(
                BaseResponseStatus.SUCCESS
        );
    }
    @Operation( security = @SecurityRequirement(name = "Bearer Auth"),
            summary = "최근 검색어 전체 삭제", description = "회원의 최근 검색어 전체 삭제", tags = {"최근검색어"})
    @PostMapping("/search/word/delete-all")
    public BaseResponse<Void> deleteUserSearchKeyV1(
            @AuthenticationPrincipal AuthUserDetail authUserDetail){
        redisService.deleteUserSearchKey(authUserDetail.getLoginId());
        return new BaseResponse<>(
                BaseResponseStatus.SUCCESS
        );
    }


}
