package com.hummingbird.kr.starbuckslike.cart.infrastructure.search;

import com.hummingbird.kr.starbuckslike.cart.dto.out.ResponseCartItemDto;
import com.hummingbird.kr.starbuckslike.cart.dto.out.ResponseCartItemImageDto;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class CartSearchTest {
    @Autowired
    CartSearch cartSearch;
    @Test //장바구니 id로 담은 상품옵션의 대표 이미지 조회
    void findCartMainImageById(){
        ResponseCartItemImageDto result = cartSearch.findCartMainImageDtoById(3L);
        log.info(result);
    }

    @Test // 장바구니 id로 담은 옵션 정보 조회
    void findCartItemDtoById(){
        ResponseCartItemDto result = cartSearch.findCartItemDtoById(1L);
        log.info(result);
    }

}