package com.hummingbird.kr.starbuckslike.temp.repository.search;

import com.hummingbird.kr.starbuckslike.temp.dto.ProductListDto;
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
    void testFindProductListById(){

        List<ProductListDto> productListById = productSearch.findProductListById(1L);
        for (ProductListDto dto : productListById) {
            log.info(dto);
        }

    }

}