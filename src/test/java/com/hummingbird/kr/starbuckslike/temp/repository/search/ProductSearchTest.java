package com.hummingbird.kr.starbuckslike.temp.repository.search;

import com.hummingbird.kr.starbuckslike.product.dto.ProductDetailDto;
import com.hummingbird.kr.starbuckslike.product.dto.ProductImageDto;
import com.hummingbird.kr.starbuckslike.product.dto.ProductListDto;
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
        ProductDetailDto result = productSearch.findProductDetailDtoById((1L));
        log.info(result);
    }

    @Test
    void findProductImageDtoById(){
        List<ProductImageDto> result = productSearch.findProductImageDtoById(1L);
        result.forEach(log::info);
    }

}