package com.hummingbird.kr.starbuckslike.config;

import com.hummingbird.kr.starbuckslike.common.utils.PurchaseCodeGenerator;
import com.hummingbird.kr.starbuckslike.product.domain.QProduct;
import com.hummingbird.kr.starbuckslike.product.domain.Wish;
import com.hummingbird.kr.starbuckslike.product.infrastructure.WishRepository;
import com.hummingbird.kr.starbuckslike.review.domain.Review;
import com.hummingbird.kr.starbuckslike.review.infrastructure.ReviewRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

import static com.hummingbird.kr.starbuckslike.product.domain.QProduct.*;

/**
 * 테스트용 데이터
 * yml 파일 ddl-auto: create 되어 있어야 함
 * @author 허정현
 */
@Configuration
@ComponentScan(basePackages = "com.hummingbird.kr.starbuckslike")
@RequiredArgsConstructor
@Log4j2
public class LoadTestData {
    private final JPAQueryFactory queryFactory;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



    // CommandLineRunner : 애플리케이션 구동 후 코드 실행하는 인터페이스
    @Bean
    CommandLineRunner initDatabase(
            WishRepository wishRepository,
            ReviewRepository reviewRepository
    ) {
        return args -> {
//            Long maxProductId = queryFactory.select(product.id)
//                    .from(product)
//                    .orderBy(product.id.desc())
//                    .fetchFirst();
//            log.info("maxProductId : "+maxProductId);
//            for (int i = 0; i < 40000; i++) {
//                // 랜덤 memberUid 생성
//                String memberUid = "test:"+UUID.randomUUID().toString();
//
//                // productId는 1, 2, 3 중 하나로 랜덤 선택
//                Long productId = (long) (Math.random() * maxProductId) + 1;
//
//                // isWished는 랜덤하게 true 또는 false 설정
//                Boolean isWished = Math.random() < 0.5;
//
//                // Wish 객체 생성
//                Wish wish = Wish.builder()
//                        .memberUID(memberUid)
//                        .productId(productId)
//                        .isWished(isWished)
//                        .build();
//
//                // 저장
//                wishRepository.save(wish);
//            }
//            for (int i = 0; i < 10000; i++) {
//                reviewRepository.save(Review.builder()
//                        .purchaseCode(PurchaseCodeGenerator.generateOrderCode())// 무작위 주문
//                        .purchaseProductId(1L) //
//                        .nickname("테스트 닉네임")
//                        .memberUID("test:"+UUID.randomUUID().toString())
//                        .productId((long) (Math.random() * maxProductId) + 1) // 1~ 최대 상품번호
//                        .optionId(1L)// 상품옵션 별로 평점관리할게 아니라 괜찮음
//                        .content("테스트 리뷰")
//                        .star((int) (Math.random() * 5) + 1) // 1~5 사이의 랜덤 정수 값 생성
//                        .commentCount(0)
//                        .isPhoto(false)
//                        .isDeleted(false)
//                        .build());
//
//            }

        };
    }
}
