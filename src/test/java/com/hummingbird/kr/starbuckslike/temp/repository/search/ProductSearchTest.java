package com.hummingbird.kr.starbuckslike.temp.repository.search;

import com.hummingbird.kr.starbuckslike.product.dto.out.ProductDetailResponseDto;
import com.hummingbird.kr.starbuckslike.product.dto.out.ProductImageResponseDto;
import com.hummingbird.kr.starbuckslike.product.infrastructure.search.ProductSearch;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
class ProductSearchTest {

    @Autowired
    private ProductSearch productSearch;

    @Test
    void findProductDetailDtoById(){
        ProductDetailResponseDto result = productSearch.findProductDetailDtoById((1L));
        log.info(result);
    }

    @Test
    void findProductImageDtoById(){
        List<ProductImageResponseDto> result = productSearch.findProductImageDtoById(1L);
        result.forEach(log::info);
    }

}