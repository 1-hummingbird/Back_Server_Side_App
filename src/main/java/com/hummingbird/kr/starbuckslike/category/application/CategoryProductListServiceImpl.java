package com.hummingbird.kr.starbuckslike.category.application;

import com.hummingbird.kr.starbuckslike.category.dto.in.CategoryProductListRequestDto;
import com.hummingbird.kr.starbuckslike.category.infrastructure.CategoryProductListRepository;
import com.hummingbird.kr.starbuckslike.product.infrastructure.ProductRepository;
import com.hummingbird.kr.starbuckslike.common.Exception.BaseException;
import com.hummingbird.kr.starbuckslike.common.entity.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryProductListServiceImpl implements CategoryProductListService {


    private final CategoryProductListRepository categoryProductListRepository;
    private final ProductRepository productRepository;

    @Override
    public void addProductByCategories(CategoryProductListRequestDto categoryProductListRequestDto) {
        categoryProductListRepository.save(categoryProductListRequestDto.toEntity(
                productRepository.findById(categoryProductListRequestDto.getProductId()).orElseThrow(() ->
                        new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT))
        ));
    }
}
