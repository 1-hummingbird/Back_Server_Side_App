package com.hummingbird.kr.starbuckslike.category.presentation;

import com.hummingbird.kr.starbuckslike.category.application.CategoryService;
import com.hummingbird.kr.starbuckslike.category.dto.in.BottomCategoryRequestDto;
import com.hummingbird.kr.starbuckslike.category.dto.in.MiddleCategoryRequestDto;
import com.hummingbird.kr.starbuckslike.category.dto.in.TopCategoryRequestDto;
import com.hummingbird.kr.starbuckslike.category.dto.out.*;
import com.hummingbird.kr.starbuckslike.category.vo.*;
import com.hummingbird.kr.starbuckslike.common.entity.BaseResponse;
import com.hummingbird.kr.starbuckslike.common.entity.BaseResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    // 메인 카테고리 조회
    @Operation(summary = "메인 카테고리 조회", description = "메인 카테고리(코드,이름,이미지) 조회")
    @GetMapping("/main")
    public BaseResponse<List<MainCategoryResponseVo>> findMainCategoryResponseDtoV1() {
        List<MainCategoryResponseVo> res = categoryService.findMainCategoryResponseDto()
                .stream()
                .map(MainCategoryResponseDto::toVo)
                .toList();

        return new BaseResponse<>(res);
    }

    // 대 카테고리 생성
    @PostMapping("/top-category")
    @Operation(summary = "대 카테고리 생성", description = "대 카테고리 생성 카테고리명, 소개 입력")
    public BaseResponse<Void> createTopCategory(
            @RequestBody TopCategoryRequestVo topCategoryRequestVo) {

        log.info("topCategoryRequestVo : {}", topCategoryRequestVo);
        TopCategoryRequestDto topCategoryRequestDto = TopCategoryRequestDto.builder()
                .topCategoryName(topCategoryRequestVo.getTopCategoryName())
                .topCategoryDescription(topCategoryRequestVo.getTopCategoryDescription())
                .build();
        categoryService.createTopCategory(topCategoryRequestDto);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @Operation(summary = "대 카테고리 조회", description = "카테고리 코드로 대 카테고리 단건 조회")
    @GetMapping("/top-category/{topCategoryCode}")
    public BaseResponse<TopCategoryResponseVo> getTopCategory(
            @PathVariable("topCategoryCode") String topCategoryCode) {
        log.info("topCategoryCode : {}", topCategoryCode);
        return new BaseResponse<>(categoryService.getTopCategoryByCategoryCode(topCategoryCode).toVo());
    }

    @Operation(summary = "중 카테고리 생성", description = "중 카테고리 생성 카테고리명, 소개 , 대 카테고리 코드 입력")
    @PostMapping("/middle-category")
    public BaseResponse<Void> createMiddleCategory(
            @RequestBody MiddleCategoryRequestVo middleCategoryRequestVo) {

        MiddleCategoryRequestDto middleCategoryRequestDto = MiddleCategoryRequestDto.builder()
                .middleCategoryName(middleCategoryRequestVo.getMiddleCategoryName())
                .middleCategoryDescription(middleCategoryRequestVo.getMiddleCategoryDescription())
                .topCategoryCode(middleCategoryRequestVo.getTopCategoryCode())
                .build();
        log.info("middleCategoryRequestDto : {}", middleCategoryRequestDto);
        categoryService.createMiddleCategory(middleCategoryRequestDto);

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @GetMapping("/middle-category/{middleCategoryCode}")
    public BaseResponse<MiddleCategoryResponseVo> getMiddleCategory(
            @PathVariable("middleCategoryCode") String middleCategoryCode) {
        log.info("middleCategoryCode : {}", middleCategoryCode);
        return new BaseResponse<>(categoryService.getMiddleCategoryByCategoryCode(middleCategoryCode).toVo());
    }

    @PostMapping("/bottom-category")
    public BaseResponse<Void> createBottomCategory(
            @RequestBody BottomCategoryRequestVo bottomCategoryRequestVo) {

        log.info("bottomCategoryRequestVo : {}", bottomCategoryRequestVo);
        BottomCategoryRequestDto bottomCategoryRequestDto = BottomCategoryRequestDto.builder()
                .bottomCategoryName(bottomCategoryRequestVo.getBottomCategoryName())
                .bottomCategoryDescription(bottomCategoryRequestVo.getBottomCategoryDescription())
                .middleCategoryCode(bottomCategoryRequestVo.getMiddleCategoryCode())
                .build();
        log.info("bottomCategoryRequestDto : {}", bottomCategoryRequestDto);
        categoryService.createBottomCategory(bottomCategoryRequestDto);

        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @GetMapping("/bottom-category/{bottomCategoryCode}")
    public BaseResponse<BottomCategoryResponseVo> getBottomCategory(
            @PathVariable("bottomCategoryCode") String bottomCategoryCode) {
        log.info("bottomCategoryCode : {}", bottomCategoryCode);
        return new BaseResponse<>(categoryService.getBottomCategoryByCategoryCode(bottomCategoryCode).toVo());
    }

    @GetMapping("/top-categories")
    public BaseResponse<List<TopCategoryResponseVo>> getTopCategories() {

        return new BaseResponse<>(
                categoryService.getTopCategories()
                        .stream()
                        .map(TopCategoryResponseDto::toVo)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/middle-categories/{topCategoryName}")
    public BaseResponse<List<MiddleCategoryResponseVo>> getMiddleCategories(
            @PathVariable("topCategoryName") String topCategoryName) {

        return new BaseResponse<>(
                categoryService.getMiddleCategories(topCategoryName)
                        .stream()
                        .map(MiddleCategoryResponseDto::toVo)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/bottom-categories/{middleCategoryCode}")
    public BaseResponse<List<BottomCategoryResponseVo>> getBottomCategories(
            @PathVariable("middleCategoryCode") String middleCategoryCode) {

        return new BaseResponse<>(
                categoryService.getBottomCategories(middleCategoryCode)
                        .stream()
                        .map(BottomCategoryResponseDto::toVo)
                        .collect(Collectors.toList()));
    }

    @Operation(summary = "대 카테고리의 자식 카테고리 조회", description = "중 카테고리의 자식까지만 조회됨. 하 카테고리는 조회안됨")
    @GetMapping("/top-category/child/{categoryCode}")
    public BaseResponse<List<ChildCategoryResponseVo>> findChildCategoriesByTopCategoryV1(
            @PathVariable("categoryCode") String categoryCode) {

        return new BaseResponse<>(
                categoryService.findChildCategoriesByTopCategory(categoryCode)
                        .stream()
                        .map(ChildCategoryResponseDto::toVo)
                        .collect(Collectors.toList()));
    }

}