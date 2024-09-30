package com.hummingbird.kr.starbuckslike.config;

import com.hummingbird.kr.starbuckslike.common.utils.PurchaseCodeGenerator;
import com.hummingbird.kr.starbuckslike.product.domain.Product;
import com.hummingbird.kr.starbuckslike.product.domain.ProductOption;
import com.hummingbird.kr.starbuckslike.product.domain.QProduct;
import com.hummingbird.kr.starbuckslike.product.domain.Wish;
import com.hummingbird.kr.starbuckslike.product.infrastructure.ProductOptionRepository;
import com.hummingbird.kr.starbuckslike.product.infrastructure.ProductRepository;
import com.hummingbird.kr.starbuckslike.product.infrastructure.WishRepository;
import com.hummingbird.kr.starbuckslike.purchase.application.PurchaseService;
import com.hummingbird.kr.starbuckslike.purchase.domain.Purchase;
import com.hummingbird.kr.starbuckslike.purchase.dto.in.AddPurchaseItemRequestDto;
import com.hummingbird.kr.starbuckslike.purchase.dto.in.AddPurchaseRequestDto;
import com.hummingbird.kr.starbuckslike.purchase.infrastructure.PurchaseRepository;
import com.hummingbird.kr.starbuckslike.redis.facade.ReviewLockFacade;
import com.hummingbird.kr.starbuckslike.review.application.ReviewService;
import com.hummingbird.kr.starbuckslike.review.domain.Review;
import com.hummingbird.kr.starbuckslike.review.domain.ReviewComment;
import com.hummingbird.kr.starbuckslike.review.dto.in.AddReviewCommentRequestDto;
import com.hummingbird.kr.starbuckslike.review.infrastructure.ReviewCommentRepository;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
            ReviewRepository reviewRepository,
            ReviewCommentRepository reviewCommentRepository,
            ReviewLockFacade reviewLockFacade,
            PurchaseService purchaseService,
            ProductRepository productRepository,
            ProductOptionRepository productOptionRepository

    ) {

        return args -> {
//            for (int i = 0; i < 200; i++) {
//`
//                Random random = new Random();
//                Long maxProductId = queryFactory.select(product.id)
//                        .from(product)
//                        .orderBy(product.id.desc())
//                        .fetchFirst();
//                Long randomProductId = (long) (Math.random() * maxProductId) + 1;
//
//                Product product = productRepository.findById(randomProductId).orElseThrow();
//                ProductOption productOption = productOptionRepository.findById(randomProductId).orElseThrow();
//                List<AddPurchaseItemRequestDto> itemList = new ArrayList<>();
//
//                for(int j = 0; j < random.nextInt(1,3); j++){
//                    AddPurchaseItemRequestDto itemDto = AddPurchaseItemRequestDto.builder()
//                            .qty(random.nextInt(3) + 1)
//                            .price(random.nextLong(40000) + 20000)
//                            .discountPrice(random.nextLong(3000) + 1000)
//                            .productId(randomProductId)
//                            .productName(product.getName())
//                            .optionId(randomProductId)
//                            .optionName(productOption.getName())
//                            .build();
//                    itemList.add(itemDto);
//                }
//                //
//                AddPurchaseRequestDto purchaseDto = AddPurchaseRequestDto
//                        .builder()
//                        .totalPrice(random.nextLong(100000) + 30000)
//                        .totalDiscount(random.nextLong(20000) + 3000)
//                        .address("테스트 주소")
//                        .primaryPhone("01011112222")
//                        .secondaryPhone("01033334444")
//                        .userName("김한별")
//                        .memberUID("afcd81bc-6513-45b6-b8ed-c3583e0a7694")
//                        .memo("안전하게 와주세요")
//                        .purchaseProducts(itemList)
//                        .build();
//                //
//                purchaseService.addPurchase(purchaseDto);
//            }



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

//            Long maxProductId = queryFactory.select(product.id)
//                        .from(product)
//                        .orderBy(product.id.desc())
//                        .fetchFirst();
//            Long randomProductId = (long) (Math.random() * maxProductId) + 1;
//            for (int i = 0; i < 10000; i++) {
//                reviewRepository.save(Review.builder()
//                        .purchaseCode(PurchaseCodeGenerator.generateOrderCode())// 무작위 주문
//                        .purchaseProductId(1L) //
//                        .nickname("테스트 닉네임")
//                        .memberUID("test:"+UUID.randomUUID().toString())
//                        .productId(randomProductId) // 1~ 최대 상품번호
//                        .optionId(randomProductId)//
//                        .content("테스트 리뷰")
//                        .star((int) (Math.random() * 5) + 1) // 1~5 사이의 랜덤 정수 값 생성
//                        .commentCount(0)
//                        .isPhoto(false)
//                        .isDeleted(false)
//                        .build());
//            }

//            List<String> randomComment = new ArrayList<>();
//            randomComment.add("정말 유용한 정보네요!");
//            randomComment.add("다음에도 이런 정보 기대할게요.");
//            randomComment.add("친절한 설명 감사합니다.");
//            randomComment.add("도움이 많이 됐어요.");
//            randomComment.add("이 상품 정말 추천해요!");
//            randomComment.add("가격 대비 훌륭하네요.");
//            randomComment.add("친구들에게도 추천했어요.");
//            randomComment.add("빠른 배송 감사합니다.");
//            randomComment.add("사용해보니 정말 좋네요.");
//            randomComment.add("품질이 좋아 만족스럽습니다.");
//            randomComment.add("이 상품 어떤가요?");
//            randomComment.add("이 상품 내구도는 좋나요?");
//            randomComment.add("이 상품 추천 하시나요?");
//            randomComment.add("몇번이나 구매하셨어요?");
//            randomComment.add("아쉬운 점은 없나요?");
//            randomComment.add("또 구매 하시나요?");
//            randomComment.add("또 구매하고 싶을 정도로 마음에 드네요.");

//            List<String> randomNickname = new ArrayList<>();
//            randomNickname.add("커피사랑");
//            randomNickname.add("초코라떼매니아");
//            randomNickname.add("에스프레소러버");
//            randomNickname.add("바닐라크림");
//            randomNickname.add("아메리카노홀릭");
//            randomNickname.add("카페모카맨");
//            randomNickname.add("돌체라떼 러버");
//            randomNickname.add("카라멜조아");
//            randomNickname.add("스타라떼");
//            randomNickname.add("그린티라떼");
//            randomNickname.add("콜드브루");
//            randomNickname.add("따뜻한카푸치노");
//            randomNickname.add("화이트모카");
//            randomNickname.add("블랙커피");
//            randomNickname.add("스무디왕");
//            randomNickname.add("핫초코매니아");
//            randomNickname.add("얼죽아러버");
//            randomNickname.add("카라멜향커피");
//            randomNickname.add("달달한라떼");
//            randomNickname.add("티라미수프라푸치노");
//            for (int i = 1001; i <= 10000; i++) {
//                for (int j = 0; j < (int) ((Math.random() * 5)); j++) {
//                    AddReviewCommentRequestDto dto = AddReviewCommentRequestDto.builder()
//                            .reviewId((long) i)
//                            .nickname(randomNickname.get((int) ((Math.random() * randomNickname.size()))))
//                            .memberUID(UUID.randomUUID().toString())
//                            .content(randomComment.get((int) ((Math.random() * randomComment.size()))))
//                            .build();
//                    reviewLockFacade.addReviewCommentAndIncreaseCount(dto);
//                }
//            }

        };
    }
}
