package com.hummingbird.kr.starbuckslike.category.application;

import com.hummingbird.kr.starbuckslike.category.domain.BottomCategory;
import com.hummingbird.kr.starbuckslike.category.domain.MiddleCategory;
import com.hummingbird.kr.starbuckslike.category.domain.TopCategory;
import com.hummingbird.kr.starbuckslike.category.dto.in.BottomCategoryRequestDto;
import com.hummingbird.kr.starbuckslike.category.dto.in.MiddleCategoryRequestDto;
import com.hummingbird.kr.starbuckslike.category.dto.in.TopCategoryRequestDto;
//import com.hummingbird.kr.starbuckslike.category.dto.out.BottomCategoryResponseDto;
//import com.hummingbird.kr.starbuckslike.category.dto.out.MainCategoryResponseDto;
//import com.hummingbird.kr.starbuckslike.category.dto.out.MiddleCategoryResponseDto;
//import com.hummingbird.kr.starbuckslike.category.dto.out.TopCategoryResponseDto;
import com.hummingbird.kr.starbuckslike.category.infrastructure.BottomCategoryRepository;
import com.hummingbird.kr.starbuckslike.category.infrastructure.MiddleCategoryRepository;
import com.hummingbird.kr.starbuckslike.category.infrastructure.TopCategoryRepository;
import com.hummingbird.kr.starbuckslike.category.infrastructure.search.CategorySearch;
import com.hummingbird.kr.starbuckslike.common.utils.CategoryCodeGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService{

    private final TopCategoryRepository topCategoryRepository;
    private final MiddleCategoryRepository middleCategoryRepository;
    private final BottomCategoryRepository bottomCategoryRepository;
    private final CategorySearch categorySearch;

    private static final int MAX_CODE_TRIES = 5;  // 최대 재시도 횟수

    @Transactional(readOnly = true)
    @Override
    public List<MainCategoryResponseDto> findMainCategoryResponseDto() {
        return categorySearch.findMainCategoryResponseDto();
    }

    @Transactional
    @Override
    public void createTopCategory(TopCategoryRequestDto topCategoryRequestDto) {

        if (topCategoryRepository.existsByCategoryName(topCategoryRequestDto.getTopCategoryName())) {
            throw new IllegalArgumentException(
                    "이미 존재하는 카테고리 이름입니다: " + topCategoryRequestDto.getTopCategoryName());
        }

        String categoryCode = generateUniqueCategoryCode("TC-");
        try {
            topCategoryRepository.save(topCategoryRequestDto.toEntity(categoryCode));
        } catch (IllegalArgumentException e) {
            log.warn("Validation failed: {}", e.getMessage());
            throw e;  // rethrow the exception to be handled by the caller or a global exception handler
        } catch (Exception e) {
            log.error("An unexpected error occurred: ", e);
            throw new RuntimeException("카테고리 생성 중 오류가 발생했습니다.", e);
        }

    }

    @Transactional
    @Override
    public void createMiddleCategory(MiddleCategoryRequestDto middleCategoryRequestDto) {

        if( middleCategoryRepository.existsByCategoryName(middleCategoryRequestDto.getMiddleCategoryName()) ){
            throw new IllegalArgumentException(
                    "이미 존재하는 카테고리 이름입니다: " + middleCategoryRequestDto.getMiddleCategoryName());
        }

        try {
            TopCategory topCategory = topCategoryRepository.findByCategoryCode(
                    middleCategoryRequestDto.getTopCategoryCode()).orElseThrow(
                    () -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다.")
            );

            String categoryCode = generateUniqueCategoryCode("MC-");
            middleCategoryRepository.save(middleCategoryRequestDto.toEntity(topCategory, categoryCode));
        } catch (IllegalArgumentException e) {
            log.warn("Validation failed: {}", e.getMessage());
            throw e;  // rethrow the exception to be handled by the caller or a global exception handler
        } catch (Exception e) {
            log.error("An unexpected error occurred: ", e);
            throw new RuntimeException("카테고리 생성 중 오류가 발생했습니다.", e);
        }

    }

    @Transactional
    @Override
    public void createBottomCategory(BottomCategoryRequestDto bottomCategoryRequestDto) {

        if( bottomCategoryRepository.existsByCategoryName(bottomCategoryRequestDto.getBottomCategoryName()) ){
            throw new IllegalArgumentException(
                    "이미 존재하는 카테고리 이름입니다: " + bottomCategoryRequestDto.getBottomCategoryName());
        }

        try {
            MiddleCategory middleCategory = middleCategoryRepository.findByCategoryCode(
                    bottomCategoryRequestDto.getMiddleCategoryCode()).orElseThrow(
                    () -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다.")
            );

            String categoryCode = generateUniqueCategoryCode("BC-");
            bottomCategoryRepository.save(bottomCategoryRequestDto.toEntity(middleCategory, categoryCode));
        } catch (IllegalArgumentException e) {
            log.warn("Validation failed: {}", e.getMessage());
            throw e;  // rethrow the exception to be handled by the caller or a global exception handler
        } catch (Exception e) {
            log.error("An unexpected error occurred: ", e);
            throw new RuntimeException("카테고리 생성 중 오류가 발생했습니다.", e);
        }

    }

    @Override
    public void updateTopCategory(TopCategoryRequestDto topCategoryRequestDto) {

    }

    @Override
    public void updateMiddleCategory(MiddleCategoryRequestDto middleCategoryRequestDto) {

    }

    @Override
    public void updateBottomCategory(BottomCategoryRequestDto bottomCategoryRequestDto) {

    }

    @Override
    public void deleteTopCategory(Long topCategoryId) {

    }

    @Override
    public void deleteMiddleCategory(Long middleCategoryId) {

    }

    @Override
    public void deleteBottomCategory(Long bottomCategoryId) {

    }

    @Override
    public TopCategoryResponseDto getTopCategory(Long topCategoryId) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public TopCategoryResponseDto getTopCategoryByCategoryCode(String topCategoryCode) {

        try {
            TopCategory topCategory = topCategoryRepository
                    .findByCategoryCode(topCategoryCode).orElseThrow(
                    () -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다.")
            );
            log.info("topCategory : {}", topCategory);
            return TopCategoryResponseDto.builder()
                    .topCategoryName(topCategory.getCategoryName())
                    .topCategoryDescription(topCategory.getCategoryDescription())
                    .topCategoryCode(topCategory.getCategoryCode())
                    .build();
        } catch (Exception e) {
            log.error("error : {}", e);
        }
        return null;

    }

    @Override
    public MiddleCategoryResponseDto getMiddleCategory(Long middleCategoryId) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public MiddleCategoryResponseDto getMiddleCategoryByCategoryCode(String middleCategoryCode) {

        try {
            MiddleCategory middleCategory = middleCategoryRepository
                    .findByCategoryCode(middleCategoryCode).orElseThrow(
                    () -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다.")
            );
            log.info("middleCategory : {}", middleCategory);
            return MiddleCategoryResponseDto.builder()
                    .middleCategoryName(middleCategory.getCategoryName())
                    .middleCategoryDescription(middleCategory.getCategoryDescription())
                    .middleCategoryCode(middleCategory.getCategoryCode())
                    .topCategoryCode(middleCategory.getTopCategory().getCategoryCode())
                    .build();
        } catch (Exception e) {
            log.error("error : {}", e);
        }
        return null;

    }

    @Override
    public BottomCategoryResponseDto getBottomCategory(Long bottomCategoryId) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public BottomCategoryResponseDto getBottomCategoryByCategoryCode(String bottomCategoryCode) {

        try {
            BottomCategory bottomCategory = bottomCategoryRepository
                    .findByCategoryCode(bottomCategoryCode).orElseThrow(
                    () -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다.")
            );
            log.info("bottomCategory : {}", bottomCategory);
            return BottomCategoryResponseDto.builder()
                    .bottomCategoryName(bottomCategory.getCategoryName())
                    .bottomCategoryDescription(bottomCategory.getCategoryDescription())
                    .bottomCategoryCode(bottomCategory.getCategoryCode())
                    .middleCategoryCode(bottomCategory.getMiddleCategory().getCategoryCode())
                    .build();
        } catch (Exception e) {
            log.error("error : {}", e);
        }
        return null;

    }

    @Override
    public List<TopCategoryResponseDto> getTopCategories() {
        return topCategoryRepository.findAll().stream().map(
                topCategory -> TopCategoryResponseDto.builder()
                        .topCategoryName(topCategory.getCategoryName())
                        .topCategoryDescription(topCategory.getCategoryDescription())
                        .topCategoryCode(topCategory.getCategoryCode())
                        .build()
        ).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<MiddleCategoryResponseDto> getMiddleCategories(String topCategoryName) {

        try {
//            TopCategory topCategory = topCategoryRepository.findByCategoryName(topCategoryName)
//                    .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다."));
//
//            log.info("topCategory : {}", topCategory);

            List<MiddleCategoryResponseDto> middleCategoryResponseDtos = middleCategoryRepository
                    .findByTopCategoryCategoryName(topCategoryName)
                    .stream()
                    .map(middleCategory -> MiddleCategoryResponseDto.builder()
                            .middleCategoryName(middleCategory.getCategoryName())
                            .middleCategoryDescription(middleCategory.getCategoryDescription())
                            .middleCategoryCode(middleCategory.getCategoryCode())
                            .topCategoryCode(middleCategory.getTopCategory().getCategoryCode())
                            .build())
                    .collect(Collectors.toList());

            log.info("middleCategories : {}", middleCategoryResponseDtos);
            return middleCategoryResponseDtos;

        } catch (IllegalArgumentException e) {
            log.warn("Validation failed: {}", e.getMessage());
            throw e;  // rethrow the exception to be handled by the caller or a global exception handler
        } catch (Exception e) {
            log.error("An unexpected error occurred: ", e);
            throw new RuntimeException("카테고리 조회 중 오류가 발생했습니다.", e);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<BottomCategoryResponseDto> getBottomCategories(String middleCategoryCode) {

        try {
            MiddleCategory middleCategory = middleCategoryRepository.findByCategoryCode(middleCategoryCode)
                    .orElseThrow(
                    () -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다.")
            );
            log.info("middleCategory : {}", middleCategory);
            List<BottomCategory> bottomCategories = bottomCategoryRepository
                    .findByMiddleCategoryCategoryCode(middleCategory.getCategoryCode());
            log.info("bottomCategories : {}", bottomCategories);
            return bottomCategories.stream().map(
                    bottomCategory -> BottomCategoryResponseDto.builder()
                            .bottomCategoryName(bottomCategory.getCategoryName())
                            .bottomCategoryDescription(bottomCategory.getCategoryDescription())
                            .bottomCategoryCode(bottomCategory.getCategoryCode())
                            .middleCategoryCode(bottomCategory.getMiddleCategory().getCategoryCode())
                            .build()
            ).collect(Collectors.toList());

        } catch (IllegalArgumentException e) {
            log.warn("Validation failed: {}", e.getMessage());
            throw e;  // rethrow the exception to be handled by the caller or a global exception handler
        } catch (Exception e) {
            log.error("An unexpected error occurred: ", e);
            throw new RuntimeException("카테고리 조회 중 오류가 발생했습니다.", e);
        }

    }

    private String generateUniqueCategoryCode(String prefix) {
        for (int i = 0; i < MAX_CODE_TRIES; i++) {
            String categoryCode = CategoryCodeGenerator.generateCategoryCode(prefix);
            switch (prefix) {
                case "TC-":
                    if (!topCategoryRepository.existsByCategoryCode(categoryCode)) {
                        return categoryCode;  // 중복이 없으면 코드 반환
                    }
                    break;
                case "MC-":
                    if (!middleCategoryRepository.existsByCategoryCode(categoryCode)) {
                        return categoryCode;  // 중복이 없으면 코드 반환
                    }
                    break;
                case "BC-":
                    if (!bottomCategoryRepository.existsByCategoryCode(categoryCode)) {
                        return categoryCode;  // 중복이 없으면 코드 반환
                    }
                    break;
                default:
                    throw new IllegalArgumentException("유효하지 않은 카테고리 코드 접두사입니다: " + prefix);
            }

        }
        throw new IllegalStateException("고유한 카테고리 코드를 생성하는 데 실패했습니다.");
    }

}
