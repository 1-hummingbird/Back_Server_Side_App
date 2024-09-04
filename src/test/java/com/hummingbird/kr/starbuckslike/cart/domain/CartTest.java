package com.hummingbird.kr.starbuckslike.cart.domain;

import com.hummingbird.kr.starbuckslike.product.domain.Product;
import com.hummingbird.kr.starbuckslike.product.domain.ProductOption;
import com.hummingbird.kr.starbuckslike.product.infrastructure.ProductOptionRepository;
import com.hummingbird.kr.starbuckslike.product.infrastructure.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@Log4j2
@SpringBootTest
class CartTest {
    @Autowired
    ProductOptionRepository productOptionRepository;


    @Test // 장바구니 추가 테스트
    void addOptionQty(){
        Optional<ProductOption> optionalOption = productOptionRepository.findById(1L);
        ProductOption productOption = optionalOption.orElseThrow();
        Cart newCart = Cart.builder()
                .productOption(productOption)
                .userUid("testUid")
                .qty(5)
                .build();
        newCart.addOptionQty(3);
        org.assertj.core.api.Assertions.assertThat(8).isEqualTo(newCart.getQty());
    }
    @Test // 장바구니 아이템 선택수량 증가 테스트
    void increaseCartItemQuantity(){
        Optional<ProductOption> optionalOption = productOptionRepository.findById(1L);
        ProductOption productOption = optionalOption.orElseThrow();
        Cart newCart = Cart.builder()
                .productOption(productOption)
                .userUid("testUid")
                .qty(5)
                .build();
        newCart.increaseCartItemQuantity();
        org.assertj.core.api.Assertions.assertThat(6).isEqualTo(newCart.getQty());
    }

    @Test // 장바구니 아이템 선택수량 감소 테스트
    void decreaseCartItemQuantity(){
        Optional<ProductOption> optionalOption = productOptionRepository.findById(1L);
        ProductOption productOption = optionalOption.orElseThrow();
        Cart newCart = Cart.builder()
                .productOption(productOption)
                .userUid("testUid")
                .qty(2)
                .build();
        newCart.decreaseCartItemQuantity();
        org.assertj.core.api.Assertions.assertThat(1).isEqualTo(newCart.getQty());
        assertThrows(IllegalStateException.class, () -> {
            newCart.decreaseCartItemQuantity();
        });

    }


}