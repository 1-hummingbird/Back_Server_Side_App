package com.hummingbird.kr.starbuckslike.product.infrastructure.search;

import com.hummingbird.kr.starbuckslike.product.dto.out.ProductInfoResponseDto;
import com.hummingbird.kr.starbuckslike.product.dto.out.ProductIsWishedResponseDto;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class ProductSearchTest {
    @Autowired
    ProductSearch productSearch;

    @Test
    void searchWishProductIdsV1Test(){
        Pageable pageable = PageRequest.of(0, 10);
        String memberUid = "97802933-4e7e-4579-a085-e504f0bf2be2";
        productSearch.searchWishProductIdsV1(pageable,memberUid).forEach(log::info);
    }
    @Test
    void findProductInfoByIdV2Test(){
        ProductInfoResponseDto dto
                = productSearch.findProductInfoByIdV2(1L, "97802933-4e7e-4579-a085-e504f0bf2be2");
        log.info(dto);
    }

    @Test
    void findProductIsWishedResponseDtoById(){
        String memberUuid= "97802933-4e7e-4579-a085-e504f0bf2be2";
        ProductIsWishedResponseDto dto =
                productSearch.findProductIsWishedResponseDtoById(1L, memberUuid);
        log.info("dto : "+dto);
    }

    @Test
    void searchBestProductIds(){
        String topCategoryCode = "tumbler";
        productSearch.searchBestProductIds(topCategoryCode).forEach(log::info);
    }


}