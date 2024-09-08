package com.hummingbird.kr.starbuckslike.category.presentation;

import com.hummingbird.kr.starbuckslike.category.application.CategoryService;
import com.hummingbird.kr.starbuckslike.category.dto.in.BottomCategoryRequestDto;
import com.hummingbird.kr.starbuckslike.category.dto.in.MiddleCategoryRequestDto;
import com.hummingbird.kr.starbuckslike.category.dto.in.TopCategoryRequestDto;
import com.hummingbird.kr.starbuckslike.category.dto.out.BottomCategoryResponseDto;
import com.hummingbird.kr.starbuckslike.category.dto.out.MiddleCategoryResponseDto;
import com.hummingbird.kr.starbuckslike.category.dto.out.TopCategoryResponseDto;
import com.hummingbird.kr.starbuckslike.category.vo.*;
import com.hummingbird.kr.starbuckslike.common.entity.CommonResponseEntity;
import com.hummingbird.kr.starbuckslike.common.entity.CommonResponseMessage;
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

    // 대 카테고리 생성
    @PostMapping("/top-category")
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

    @GetMapping("/top-category/{topCategoryCode}")
    public CommonResponseEntity<TopCategoryResponseVo> getTopCategory(
            @PathVariable("topCategoryCode") String topCategoryCode) {
        log.info("topCategoryCode : {}", topCategoryCode);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                categoryService.getTopCategoryByCategoryCode(topCategoryCode).toVo());
    }

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


}