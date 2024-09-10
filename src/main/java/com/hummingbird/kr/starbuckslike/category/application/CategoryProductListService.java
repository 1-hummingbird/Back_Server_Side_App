package com.hummingbird.kr.starbuckslike.category.application;


import com.hummingbird.kr.starbuckslike.category.dto.in.CategoryProductListRequestDto;

public interface CategoryProductListService {

    void addProductByCategories(CategoryProductListRequestDto productCategoryListRequestDto);

}
