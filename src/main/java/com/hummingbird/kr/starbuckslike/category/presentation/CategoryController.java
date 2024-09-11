package com.hummingbird.kr.starbuckslike.category.presentation;

import com.hummingbird.kr.starbuckslike.category.application.CategoryService;
import com.hummingbird.kr.starbuckslike.category.dto.in.BottomCategoryRequestDto;
import com.hummingbird.kr.starbuckslike.category.dto.in.MiddleCategoryRequestDto;
import com.hummingbird.kr.starbuckslike.category.dto.in.TopCategoryRequestDto;
import com.hummingbird.kr.starbuckslike.category.dto.out.*;
import com.hummingbird.kr.starbuckslike.category.vo.*;
import com.hummingbird.kr.starbuckslike.common.entity.CommonResponseEntity;
import com.hummingbird.kr.starbuckslike.common.entity.CommonResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    public CommonResponseEntity<List<MainCategoryResponseVo>> findMainCategoryResponseDtoV1() {
        List<MainCategoryResponseVo> res = categoryService.findMainCategoryResponseDto()
                .stream()
                .map(MainCategoryResponseDto::toVo)
                .toList();

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                res
        );
    }

    // 대 카테고리 생성
    @PostMapping("/top-category")
    @Operation(summary = "대 카테고리 생성", description = "대 카테고리 생성 카테고리명, 소개 입력")
    public CommonResponseEntity<Void> createTopCategory(
            @RequestBody TopCategoryRequestVo topCategoryRequestVo) {

        log.info("topCategoryRequestVo : {}", topCategoryRequestVo);
        TopCategoryRequestDto topCategoryRequestDto = TopCategoryRequestDto.builder()
                .topCategoryName(topCategoryRequestVo.getTopCategoryName())
                .topCategoryDescription(topCategoryRequestVo.getTopCategoryDescription())
                .build();
        categoryService.createTopCategory(topCategoryRequestDto);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                null);
    }

    @Operation(summary = "대 카테고리 조회", description = "카테고리 코드로 대 카테고리 단건 조회")
    @GetMapping("/top-category/{topCategoryCode}")
    public CommonResponseEntity<TopCategoryResponseVo> getTopCategory(
            @PathVariable("topCategoryCode") String topCategoryCode) {
        log.info("topCategoryCode : {}", topCategoryCode);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                categoryService.getTopCategoryByCategoryCode(topCategoryCode).toVo());
    }

    @Operation(summary = "중 카테고리 생성", description = "중 카테고리 생성 카테고리명, 소개 , 대 카테고리 코드 입력")
    @PostMapping("/middle-category")
    public CommonResponseEntity<Void> createMiddleCategory(
            @RequestBody MiddleCategoryRequestVo middleCategoryRequestVo) {

        log.info("middleCategoryRequestVo : {}", middleCategoryRequestVo);
        MiddleCategoryRequestDto middleCategoryRequestDto = MiddleCategoryRequestDto.builder()
                .middleCategoryName(middleCategoryRequestVo.getMiddleCategoryName())
                .middleCategoryDescription(middleCategoryRequestVo.getMiddleCategoryDescription())
                .topCategoryCode(middleCategoryRequestVo.getTopCategoryCode())
                .build();
        log.info("middleCategoryRequestDto : {}", middleCategoryRequestDto);
        categoryService.createMiddleCategory(middleCategoryRequestDto);

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                null);
    }

    @GetMapping("/middle-category/{middleCategoryCode}")
    public CommonResponseEntity<MiddleCategoryResponseVo> getMiddleCategory(
            @PathVariable("middleCategoryCode") String middleCategoryCode) {
        log.info("middleCategoryCode : {}", middleCategoryCode);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                categoryService.getMiddleCategoryByCategoryCode(middleCategoryCode).toVo());
    }

    @PostMapping("/bottom-category")
    public CommonResponseEntity<Void> createBottomCategory(
            @RequestBody BottomCategoryRequestVo bottomCategoryRequestVo) {

        log.info("bottomCategoryRequestVo : {}", bottomCategoryRequestVo);
        BottomCategoryRequestDto bottomCategoryRequestDto = BottomCategoryRequestDto.builder()
                .bottomCategoryName(bottomCategoryRequestVo.getBottomCategoryName())
                .bottomCategoryDescription(bottomCategoryRequestVo.getBottomCategoryDescription())
                .middleCategoryCode(bottomCategoryRequestVo.getMiddleCategoryCode())
                .build();
        log.info("bottomCategoryRequestDto : {}", bottomCategoryRequestDto);
        categoryService.createBottomCategory(bottomCategoryRequestDto);

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                null);
    }

    @GetMapping("/bottom-category/{bottomCategoryCode}")
    public CommonResponseEntity<BottomCategoryResponseVo> getBottomCategory(
            @PathVariable("bottomCategoryCode") String bottomCategoryCode) {
        log.info("bottomCategoryCode : {}", bottomCategoryCode);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                categoryService.getBottomCategoryByCategoryCode(bottomCategoryCode).toVo());
    }

    @GetMapping("/top-categories")
    public CommonResponseEntity<List<TopCategoryResponseVo>> getTopCategories() {

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                categoryService.getTopCategories()
                        .stream()
                        .map(TopCategoryResponseDto::toVo)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/middle-categories/{topCategoryName}")
    public CommonResponseEntity<List<MiddleCategoryResponseVo>> getMiddleCategories(
            @PathVariable String topCategoryName) {

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                categoryService.getMiddleCategories(topCategoryName)
                        .stream()
                        .map(MiddleCategoryResponseDto::toVo)
                        .collect(Collectors.toList()));
    }

    @GetMapping("/bottom-categories/{middleCategoryCode}")
    public CommonResponseEntity<List<BottomCategoryResponseVo>> getBottomCategories(
            @PathVariable String middleCategoryCode) {

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                categoryService.getBottomCategories(middleCategoryCode)
                        .stream()
                        .map(BottomCategoryResponseDto::toVo)
                        .collect(Collectors.toList()));
    }

    @Operation(summary = "대 카테고리의 자식 카테고리 조회", description = "중 카테고리의 자식까지만 조회됨. 하 카테고리는 조회안됨")
    @GetMapping("/top-category/child/{categoryCode}")
    public CommonResponseEntity<List<ChildCategoryResponseVo>> findChildCategoriesByTopCategoryV1(
            @PathVariable("categoryCode") String categoryCode) {

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                categoryService.findChildCategoriesByTopCategory(categoryCode)
                        .stream()
                        .map(ChildCategoryResponseDto::toVo)
                        .collect(Collectors.toList()));
    }

}