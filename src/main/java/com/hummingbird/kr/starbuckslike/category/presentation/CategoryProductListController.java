package com.hummingbird.kr.starbuckslike.category.presentation;

import com.hummingbird.kr.starbuckslike.category.application.CategoryProductListService;
import com.hummingbird.kr.starbuckslike.category.dto.in.CategoryProductListRequestDto;
import com.hummingbird.kr.starbuckslike.category.vo.CategoryProductListRequestVo;
import com.hummingbird.kr.starbuckslike.common.entity.CommonResponseEntity;
import com.hummingbird.kr.starbuckslike.common.entity.CommonResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @PostMapping("/product-category-list")
    public CommonResponseEntity<Void> addProductByCategories(
            @RequestBody CategoryProductListRequestVo categoryProductListRequestVo) {

        categoryProductListService.addProductByCategories(CategoryProductListRequestDto.builder()
                .productId(categoryProductListRequestVo.getProductId())
                .topCategoryCode(categoryProductListRequestVo.getTopCategoryCode())
                .middleCategoryCode(categoryProductListRequestVo.getMiddleCategoryCode())
                .bottomCategoryCode(categoryProductListRequestVo.getBottomCategoryCode())
                .build());
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                null
        );

    }
}
