package com.hummingbird.kr.starbuckslike.category.presentation;

import com.hummingbird.kr.starbuckslike.category.application.CategoryProductListService;
import com.hummingbird.kr.starbuckslike.category.dto.in.CategoryProductListRequestDto;
import com.hummingbird.kr.starbuckslike.category.vo.CategoryProductListRequestVo;
import com.hummingbird.kr.starbuckslike.common.entity.BaseResponse;
import com.hummingbird.kr.starbuckslike.common.entity.BaseResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class CategoryProductListController {

    private final CategoryProductListService categoryProductListService;

    // 카테고리 상품 추가
    @Operation( security = @SecurityRequirement(name = "Bearer Auth"),
            summary = "카테고리에 상품 등록", description = "카테고리에 상품 등록하기", tags = {"카테고리(관리자)"})
    @PostMapping("/product-category-list")
    public BaseResponse<Void> addProductByCategories(
            @RequestBody CategoryProductListRequestVo categoryProductListRequestVo) {

        categoryProductListService.addProductByCategories(CategoryProductListRequestDto.builder()
                .productId(categoryProductListRequestVo.getProductId())
                .topCategoryCode(categoryProductListRequestVo.getTopCategoryCode())
                .middleCategoryCode(categoryProductListRequestVo.getMiddleCategoryCode())
                .bottomCategoryCode(categoryProductListRequestVo.getBottomCategoryCode())
                .build());
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }
}
