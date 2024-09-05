package com.hummingbird.kr.starbuckslike.config;


import com.hummingbird.kr.starbuckslike.banner.domain.Banner;
import com.hummingbird.kr.starbuckslike.banner.infrastructure.BannerRepository;
import com.hummingbird.kr.starbuckslike.cart.domain.Cart;
import com.hummingbird.kr.starbuckslike.cart.infrastructure.CartRepository;
import com.hummingbird.kr.starbuckslike.category.domain.Category;
import com.hummingbird.kr.starbuckslike.category.infrastructure.CategoryRepository;
import com.hummingbird.kr.starbuckslike.exhibition.domain.Exhibition;
import com.hummingbird.kr.starbuckslike.exhibition.domain.ExhibitionProduct;
import com.hummingbird.kr.starbuckslike.exhibition.infrastructure.ExhibitionProductRepository;
import com.hummingbird.kr.starbuckslike.exhibition.infrastructure.ExhibitionRepository;
import com.hummingbird.kr.starbuckslike.member.domain.Member;
import com.hummingbird.kr.starbuckslike.member.infrastructrue.MemberRepository;
import com.hummingbird.kr.starbuckslike.product.domain.Product;
import com.hummingbird.kr.starbuckslike.product.domain.ProductImage;
import com.hummingbird.kr.starbuckslike.product.domain.ProductOption;
import com.hummingbird.kr.starbuckslike.product.domain.SalesStatus;
import com.hummingbird.kr.starbuckslike.product.infrastructure.ProductImageRepository;
import com.hummingbird.kr.starbuckslike.product.infrastructure.ProductOptionRepository;
import com.hummingbird.kr.starbuckslike.product.infrastructure.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.UUID;

/**
 * 테스트용 데이터
 * yml 파일 ddl-auto: create 되어 있어야 함
 * @author 허정현
 */
@Configuration
public class LoadTestData {

    // CommandLineRunner : 애플리케이션 구동 후 코드 실행하는 인터페이스
    @Bean
    CommandLineRunner initDatabase(
            CategoryRepository categoryRepository,
            ProductRepository productRepository,
            ProductImageRepository productImageRepository,
            ProductOptionRepository productOptionRepository,
            CartRepository cartRepository,
            ExhibitionRepository exhibitionRepository,
            ExhibitionProductRepository exhibitionProductRepository,
            MemberRepository memberRepository,
            BannerRepository bannerRepository
                                   ) {
        return args -> {
            /**
             * 테스트용 임시 카테고리 데이터 추가
             */
            // depth 0단계 루트 카테고리 생성
            Category tumbler = new Category(null, "텀블러", null, 0, "" ,
                    "https://image.istarbucks.co.kr/upload/store/skuimg/2022/02/[9300000003591]_20220222165515488.jpg");
            tumbler = categoryRepository.save(tumbler);  // ID가 생성됨
            tumbler.setPath(String.valueOf(tumbler.getId()));  // ID를 사용하여 path 설정
            tumbler = categoryRepository.save(tumbler);  // path 업데이트

            Category cup = new Category(null, "컵", null, 0, "",
                    "https://image.istarbucks.co.kr/upload/store/skuimg/2024/06/[9300000005354]_20240617142521983.jpg");
            cup = categoryRepository.save(cup);
            cup.setPath(String.valueOf(cup.getId()));
            cup = categoryRepository.save(cup);

            // depth 1단계 하위 카테고리 생성
            Category stainlessTumbler = new Category(null, "스테인리스 텀블러", tumbler,
                    1, "",null);
            stainlessTumbler = categoryRepository.save(stainlessTumbler);
            stainlessTumbler.setPath(tumbler.getPath() + "/" + stainlessTumbler.getId());
            stainlessTumbler = categoryRepository.save(stainlessTumbler);

            Category steelTumbler = new Category(null, "철 텀블러", tumbler,
                    1, "",null);
            steelTumbler = categoryRepository.save(steelTumbler);
            steelTumbler.setPath(tumbler.getPath() + "/" + steelTumbler.getId());
            steelTumbler = categoryRepository.save(steelTumbler);

            Category glassCup = new Category(null, "유리 컵", cup,
                    1, "",null);
            glassCup = categoryRepository.save(glassCup);
            glassCup.setPath(cup.getPath() + "/" + glassCup.getId());
            glassCup = categoryRepository.save(glassCup);

            Category stainlessCup = new Category(null, "스테인리스 컵", cup,
                    1, "",null);
            stainlessCup = categoryRepository.save(stainlessCup);
            stainlessCup.setPath(cup.getPath() + "/" + stainlessCup.getId());
            stainlessCup = categoryRepository.save(stainlessCup);

            // depth 2단계 하위 카테고리 생성
            Category highStainlessTumbler = new Category(null, "고급 스테인리스 텀블러", stainlessTumbler,
                    2, "",null);
            highStainlessTumbler = categoryRepository.save(highStainlessTumbler);
            highStainlessTumbler.setPath(stainlessTumbler.getPath() + "/" + highStainlessTumbler.getId());
            highStainlessTumbler = categoryRepository.save(highStainlessTumbler);

            Category lowStainlessTumbler = new Category(null, "하급 스테인리스 컵", stainlessTumbler,
                    2, "",null);
            lowStainlessTumbler = categoryRepository.save(lowStainlessTumbler);
            lowStainlessTumbler.setPath(stainlessTumbler.getPath() + "/" + lowStainlessTumbler.getId());
            lowStainlessTumbler = categoryRepository.save(lowStainlessTumbler);

            Category highGlassCup = new Category(null, "고급 유리 컵", glassCup,
                    2, "",null);
            highGlassCup = categoryRepository.save(highGlassCup);
            highGlassCup.setPath(glassCup.getPath() + "/" + highGlassCup.getId());
            highGlassCup = categoryRepository.save(highGlassCup);

            Category lowGlassCup = new Category(null, "하급 유리 컵", glassCup,
                    2, "",null);
            lowGlassCup = categoryRepository.save(lowGlassCup);
            lowGlassCup.setPath(glassCup.getPath() + "/" + lowGlassCup.getId());
            lowGlassCup = categoryRepository.save(lowGlassCup);


            /**
             * 테스트용 임시 상품 데이터 추가
             */
            Product product1 = productRepository.save(Product.builder()
                    .name("스탠리 텀블러")
                    .price(30000)
                    .isDiscounted(false)
                    .discountRate(0.0f)
                    .shortDescription("스탠리 텀블러입니다.")
                    .fullDescription("<p>스탠리 텀블러 상품 상세</p>")
                    .status(SalesStatus.AVAILABLE)
                    //.maxOrder(5)
                    //.maxPeriod(30)
                    .category(tumbler)
                    .build()
            );

            Product product2 = productRepository.save(Product.builder()
                    .name("스탠리 스테인리스 텀블러")
                    .price(40000)
                    .isDiscounted(true)
                    .discountRate(10.0f)
                    .shortDescription("스탠리 스테인리스 텀블러 압나더.")
                    .fullDescription("<p>스탠리 스테인리스 텀블러 상품 상세</p>")
                    .status(SalesStatus.AVAILABLE)
                    .category(stainlessTumbler)
                    .build()
            );

            Product product3 =productRepository.save(Product.builder()
                    .name("스탠리 고급 스테인리스 텀블러")
                    .price(50000)
                    .isDiscounted(true)
                    .discountRate(15.0f)
                    .shortDescription("스탠리 고급 스테인리스 텀블러 입니다")
                    .fullDescription("<p>스탠리 고급 스테인리스 텀블러 상품 상세</p>")
                    .status(SalesStatus.AVAILABLE)
                    .category(highStainlessTumbler)
                    .build()
            );
            Product product4 =productRepository.save(Product.builder()
                    .name("펭귄 컵")
                    .price(10000)
                    .isDiscounted(true)
                    .discountRate(15.0f)
                    .shortDescription("펭귄 컵 입니다")
                    .fullDescription("<p>펭귄 컵 상품 상세</p>")
                    .status(SalesStatus.AVAILABLE)

                    .category(cup)
                    .build()
            );
            Product product5 =productRepository.save(Product.builder()
                    .name("펭귄 유리 컵")
                    .price(10000)
                    .isDiscounted(true)
                    .discountRate(15.0f)
                    .shortDescription("펭귄 유리 컵 입니다")
                    .fullDescription("<p>펭귄 유리 컵 상품 상세</p>")
                    .status(SalesStatus.AVAILABLE)
                    .category(glassCup)
                    .build()
            );
            // 상품 이미지
            productImageRepository.save(ProductImage.builder()
                    .product(product1)
                    .url("test/path/스탠리 텀블러 이미지0.jpg")
                    .seq(0)
                    .build()
            );
            productImageRepository.save(ProductImage.builder()
                    .product(product1)
                    .url("test/path/스탠리 텀블러 이미지1.jpg")
                    .seq(1)
                    .build()
            );
            productImageRepository.save(ProductImage.builder()
                    .product(product1)
                    .url("test/path/스탠리 텀블러 이미지2.jpg")
                    .seq(2)
                    .build()
            );
            productImageRepository.save(ProductImage.builder()
                    .product(product4)
                    .url("test/path/펭귄컵 이미지0.jpg")
                    .seq(0)
                    .build()
            );
            productImageRepository.save(ProductImage.builder()
                    .product(product4)
                    .url("test/path/펭귄컵 이미지1.jpg")
                    .seq(1)
                    .build()
            );


            // 상품 옵션
            ProductOption product1_option1 = ProductOption.builder()
                    .product(product1) // 스탠리 텀블러의 옵션
                    .name("스탠리 텀블러 옵션1")
                    .price(30000)
                    .quantity(20000L)
                    .isInputOption(false)
                    .discountRate(0.0f)
                    .status(SalesStatus.AVAILABLE)
                    .build();
            productOptionRepository.save(product1_option1);
            ProductOption product1_option2 = ProductOption.builder()
                    .product(product1) // 스탠리 텀블러의 옵션
                    .name("스탠리 텀블러 옵션2")
                    .price(29000)
                    .quantity(10000L)
                    .isInputOption(false)
                    .discountRate(0.0f)
                    .status(SalesStatus.AVAILABLE)
                    .build();
            productOptionRepository.save(product1_option2);

            ProductOption product4_option1 = ProductOption.builder()
                    .product(product4) // 펭퀸컵의 옵션
                    .name("펭퀸컵 옵션1")
                    .price(10000)
                    .quantity(3000L)
                    .isInputOption(true)
                    .discountRate(12.5f)
                    .status(SalesStatus.AVAILABLE)
                    .build();
            productOptionRepository.save(product4_option1);

            /**
             * 가획전 테스트 데이터
             */
            Exhibition summerExhibition = exhibitionRepository.save(
                    Exhibition.builder()
                            .name("여름 기획전")
                            .title("여름 기획전 타이틀")
                            .fullDescription("<p>여름 기획전 상세</p>")
                            .startDate(LocalDate.of(2024, 8, 1))
                            .endDate(LocalDate.of(2024, 8, 30))
                            .isDeleted(false)
                            .build()
            );
            Exhibition stanExhibition = exhibitionRepository.save(
                    Exhibition.builder()
                            .name("스탠리 기획전")
                            .title("스탠리 기획전 타이틀")
                            .fullDescription("<p>스탠리 기획전 상세</p>")
                            .startDate(LocalDate.of(2024,7,1))
                            .endDate(LocalDate.of(2024,12,25))
                            .isDeleted(false)
                            .build()
            );
            // 기획전 , 상품 중간 테이블 데이터
            exhibitionProductRepository.save(
                    ExhibitionProduct.builder()
                            .exhibition(summerExhibition) // 여름 기획전
                            .product(product1) // 스탠리 텀블러 상품 연결
                            .build()
            );
            exhibitionProductRepository.save(
                    ExhibitionProduct.builder()
                            .exhibition(stanExhibition) // 스탠리 기획전
                            .product(product2) // 스탠리 스테인리스 텀블러 상품 연결
                            .build()
            );
            exhibitionProductRepository.save(
                    ExhibitionProduct.builder()
                            .exhibition(summerExhibition) // 여름 기획전
                            .product(product3) // 스탠리 고급 스테인리스 텀블러 상품 연결
                            .build()
            );
            exhibitionProductRepository.save( // 중간 테이블 중복 데이터 생성
                    ExhibitionProduct.builder()
                            .exhibition(summerExhibition) // 여름 기획전
                            .product(product1) // 스탠리 고급 스테인리스 텀블러 상품 연결
                            .build()
            );
            /**
             * 회원 테스트 데이터
             */
            Member member1 = Member.builder()
                    .loginID("testMemberId1")
                    .name("테스트유저1")
                    .nickname("테스트유저닉네임1")
                    .birthdate("1998-01-01")
                    .phone("010-1234-5678")
                    .email("test@test.com")
                    .password("test1234")
                    .isDeleted(false)
                    .memberUID(UUID.randomUUID().toString())
                    .build();
            memberRepository.save(member1);
            Member member2 = Member.builder()
                    .loginID("testMemberId1")
                    .name("테스트유저2")
                    .nickname("테스트유저닉네임2")
                    .birthdate("1998-01-02")
                    .phone("010-1234-5678")
                    .email("test@test.com")
                    .password("test1234")
                    .isDeleted(false)
                    .memberUID(UUID.randomUUID().toString())
                    .build();
            memberRepository.save(member2);

            /**
             * 배너 테스트 데이터
             */
            bannerRepository.save(
                    Banner.builder()
                            .image("??/??/bannerImg3.jpg")
                            .seq(3)
                            .url(null)
                            .build()
            );
            bannerRepository.save(
                    Banner.builder()
                            .image("??/??/bannerImg2.jpg")
                            .seq(2)
                            .url(null)
                            .build()
            );
            bannerRepository.save(
                    Banner.builder()
                            .image("??/??/bannerImg1.jpg")
                            .seq(1)
                            .url(null)
                            .build()
            );
            /**
             * 장바구니 테스트 데이터
             */
            cartRepository.save(Cart.builder()
                    .userUid(member1.getMemberUID())
                    .product(product1_option2.getProduct())
                    .productOption(product1_option1) // 스탠리 텀블러 옵션1
                    .qty(2)
                    .isChecked(false)
                    .isDeleted(false)
                    .build()
            );
            cartRepository.save(Cart.builder()
                    .userUid(member1.getMemberUID())
                    .product(product1_option2.getProduct())
                    .productOption(product1_option2) // 스탠리 텀블러 옵션2
                    .qty(4)
                    .isChecked(false)
                    .isDeleted(false)
                    .build()
            );

            cartRepository.save(Cart.builder()
                    .userUid(member1.getMemberUID())
                    .product(product4_option1.getProduct()) //
                    .productOption(product4_option1) // 펭귄컵 옵션1
                    .qty(4)
                    .isChecked(false)
                    .isDeleted(false)
                    .build()
            );




        };
    }
}