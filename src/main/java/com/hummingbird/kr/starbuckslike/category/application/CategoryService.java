package com.hummingbird.kr.starbuckslike.category.application;


import com.hummingbird.kr.starbuckslike.category.dto.in.BottomCategoryRequestDto;
import com.hummingbird.kr.starbuckslike.category.dto.in.MiddleCategoryRequestDto;
import com.hummingbird.kr.starbuckslike.category.dto.in.TopCategoryRequestDto;
import com.hummingbird.kr.starbuckslike.category.dto.out.*;

import java.util.List;

public interface CategoryService {

    // 메인 카테고리 조회
    List<MainCategoryResponseDto> findMainCategoryResponseDto();

    // 상 카테고리 생성
    void createTopCategory(TopCategoryRequestDto topCategoryRequestDto);
    // 중 카테고리 생성
    void createMiddleCategory(MiddleCategoryRequestDto middleCategoryRequestDto);
    // 하 카테고리 생성
    void createBottomCategory(BottomCategoryRequestDto bottomCategoryRequestDto);

    void updateTopCategory(TopCategoryRequestDto topCategoryRequestDto);
    void updateMiddleCategory(MiddleCategoryRequestDto middleCategoryRequestDto);
    void updateBottomCategory(BottomCategoryRequestDto bottomCategoryRequestDto);

    void deleteTopCategory(Long topCategoryId);
    void deleteMiddleCategory(Long middleCategoryId);
    void deleteBottomCategory(Long bottomCategoryId);

    TopCategoryResponseDto getTopCategory(Long topCategoryId);
    TopCategoryResponseDto getTopCategoryByCategoryCode(String topCategoryCode);
    MiddleCategoryResponseDto getMiddleCategory(Long middleCategoryId);
    MiddleCategoryResponseDto getMiddleCategoryByCategoryCode(String middleCategoryCode);
    BottomCategoryResponseDto getBottomCategory(Long bottomCategoryId);
    BottomCategoryResponseDto getBottomCategoryByCategoryCode(String bottomCategoryCode);

    List<TopCategoryResponseDto> getTopCategories();
    List<MiddleCategoryResponseDto> getMiddleCategories(String topCategoryCode);
    List<BottomCategoryResponseDto> getBottomCategories(String middleCategoryCode);

    // 대 카테고리의 자식 카테고리(중) 조회
    List<ChildCategoryResponseDto> findChildCategoriesByTopCategory (String categoryCode);
}
