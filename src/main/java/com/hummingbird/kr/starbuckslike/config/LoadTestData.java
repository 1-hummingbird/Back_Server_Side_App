package com.hummingbird.kr.starbuckslike.config;


import com.hummingbird.kr.starbuckslike.banner.domain.Banner;
import com.hummingbird.kr.starbuckslike.banner.infrastructure.BannerRepository;
import com.hummingbird.kr.starbuckslike.cart.domain.Cart;
import com.hummingbird.kr.starbuckslike.cart.infrastructure.CartRepository;
import com.hummingbird.kr.starbuckslike.category.application.CategoryService;
import com.hummingbird.kr.starbuckslike.category.domain.CategoryProductList;
import com.hummingbird.kr.starbuckslike.category.domain.MiddleCategory;
import com.hummingbird.kr.starbuckslike.category.domain.TopCategory;
import com.hummingbird.kr.starbuckslike.category.dto.in.MiddleCategoryRequestDto;
import com.hummingbird.kr.starbuckslike.category.dto.in.TopCategoryRequestDto;
import com.hummingbird.kr.starbuckslike.category.infrastructure.CategoryProductListRepository;
import com.hummingbird.kr.starbuckslike.category.infrastructure.MiddleCategoryRepository;
import com.hummingbird.kr.starbuckslike.category.infrastructure.TopCategoryRepository;
import com.hummingbird.kr.starbuckslike.common.utils.CategoryCodeGenerator;
import com.hummingbird.kr.starbuckslike.exhibition.domain.Exhibition;
import com.hummingbird.kr.starbuckslike.exhibition.domain.ExhibitionProduct;
import com.hummingbird.kr.starbuckslike.exhibition.infrastructure.ExhibitionProductRepository;
import com.hummingbird.kr.starbuckslike.exhibition.infrastructure.ExhibitionRepository;
import com.hummingbird.kr.starbuckslike.member.domain.Member;
import com.hummingbird.kr.starbuckslike.member.infrastructrue.MemberRepository;
import com.hummingbird.kr.starbuckslike.product.domain.Product;
import com.hummingbird.kr.starbuckslike.product.domain.ProductImage;
import com.hummingbird.kr.starbuckslike.product.domain.ProductOption;
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
            TopCategoryRepository topCategoryRepository,
            MiddleCategoryRepository middleCategoryRepository,
            ProductRepository productRepository,
            CategoryProductListRepository categoryProductListRepository,
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
            // 상 카테고리
            TopCategory topCategory1 = TopCategory.builder()
                    .categoryName("대 스탠리")
                    .categoryDescription("대 스탠리 카테고리 입니다")
                    .categoryCode(CategoryCodeGenerator.generateCategoryCode("tc-"))
                    .build();
            topCategoryRepository.save(topCategory1);
            TopCategory topCategory2 = TopCategory.builder()
                    .categoryName("대 펭귄")
                    .categoryDescription("대 펭귄 카테고리 입니다")
                    .categoryCode(CategoryCodeGenerator.generateCategoryCode("tc-"))
                    .build();
            topCategoryRepository.save(topCategory2);;
            // 중 카테고리
            MiddleCategory middleCategory1 = MiddleCategory.builder()
                    .categoryName("중 스탠리 스테인리스")
                    .categoryDescription("중 스탠리 스테인리스 카테고리 입니다")
                    .categoryCode(CategoryCodeGenerator.generateCategoryCode("bc-"))
                    .topCategory(topCategory1) // 대 스탠리
                    .build();
            middleCategoryRepository.save(middleCategory1);
            MiddleCategory middleCategory2 = MiddleCategory.builder()
                    .categoryName("중 펭귄 유리컵")
                    .categoryDescription("중 펭귄 유리컵 카테고리 입니다")
                    .categoryCode(CategoryCodeGenerator.generateCategoryCode("bc-"))
                    .topCategory(topCategory2) // 대 스탠리
                    .build();
            middleCategoryRepository.save(middleCategory2);
            // 하 카테고리

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
                    .isAvailable(true)
                    .isHidden(false)
                    //.maxOrder(5)
                    //.maxPeriod(30)
                    //.category(tumbler)
                    .isDeleted(false)
                    .build()
            );

            Product product2 = productRepository.save(Product.builder()
                    .name("스탠리 스테인리스 텀블러")
                    .price(40000)
                    .isDiscounted(true)
                    .discountRate(10.0f)
                    .shortDescription("스탠리 스테인리스 텀블러 압나더.")
                    .fullDescription("<p>스탠리 스테인리스 텀블러 상품 상세</p>")
                    .isAvailable(true)
                    .isHidden(false)
                    //.category(stainlessTumbler)
                    .isDeleted(false)
                    .build()
            );

            Product product3 =productRepository.save(Product.builder()
                    .name("스탠리 고급 스테인리스 텀블러")
                    .price(50000)
                    .isDiscounted(true)
                    .discountRate(15.0f)
                    .shortDescription("스탠리 고급 스테인리스 텀블러 입니다")
                    .fullDescription("<p>스탠리 고급 스테인리스 텀블러 상품 상세</p>")
                    .isAvailable(true)
                    .isHidden(false)
                    .isDeleted(false)
                    //.category(highStainlessTumbler)
                    .build()
            );
            Product product4 =productRepository.save(Product.builder()
                    .name("펭귄 컵")
                    .price(10000)
                    .isDiscounted(true)
                    .discountRate(15.0f)
                    .shortDescription("펭귄 컵 입니다")
                    .fullDescription("<p>펭귄 컵 상품 상세</p>")
                    .isAvailable(true)
                    .isHidden(false)
                    .isDeleted(false)
                    //.category(cup)
                    .build()
            );
            Product product5 =productRepository.save(Product.builder()
                    .name("펭귄 유리 컵")
                    .price(10000)
                    .isDiscounted(true)
                    .discountRate(15.0f)
                    .shortDescription("펭귄 유리 컵 입니다")
                    .fullDescription("<p>펭귄 유리 컵 상품 상세</p>")
                    .isAvailable(true)
                    .isHidden(false)
                    .isDeleted(false)
                    //.category(glassCup)
                    .build()
            );
            // 상품 이미지
            productImageRepository.save(ProductImage.builder()
                    .product(product1)
                    .imageUrl("test/path/스탠리 텀블러 이미지0.jpg")
                    .seq(0)
                    .build()
            );
            productImageRepository.save(ProductImage.builder()
                    .product(product1)
                    .imageUrl("test/path/스탠리 텀블러 이미지1.jpg")
                    .seq(1)
                    .build()
            );
            productImageRepository.save(ProductImage.builder()
                    .product(product1)
                    .imageUrl("test/path/스탠리 텀블러 이미지2.jpg")
                    .seq(2)
                    .build()
            );
            productImageRepository.save(ProductImage.builder()
                    .product(product4)
                    .imageUrl("test/path/펭귄컵 이미지0.jpg")
                    .seq(0)
                    .build()
            );
            productImageRepository.save(ProductImage.builder()
                    .product(product4)
                    .imageUrl("test/path/펭귄컵 이미지1.jpg")
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
                    .isAvailable(true)
                    .isHidden(false)
                    .isDeleted(false)
                    .build();
            productOptionRepository.save(product1_option1);
            ProductOption product1_option2 = ProductOption.builder()
                    .product(product1) // 스탠리 텀블러의 옵션
                    .name("스탠리 텀블러 옵션2")
                    .price(29000)
                    .quantity(10000L)
                    .isInputOption(false)
                    .discountRate(0.0f)
                    .isAvailable(true)
                    .isHidden(false)
                    .isDeleted(false)
                    .build();
            productOptionRepository.save(product1_option2);

            ProductOption product4_option1 = ProductOption.builder()
                    .product(product4) // 펭퀸컵의 옵션
                    .name("펭퀸컵 옵션1")
                    .price(10000)
                    .quantity(3000L)
                    .isInputOption(true)
                    .discountRate(12.5f)
                    .isAvailable(true)
                    .isHidden(false)
                    .isDeleted(false)
                    .build();
            productOptionRepository.save(product4_option1);

            /**
             * 카테고리 <-> 상품 중간테이블
             */
            //
            categoryProductListRepository.save(
                    CategoryProductList.builder()
                            .topCategoryCode(topCategory1.getCategoryCode()) // 대 스탠리
                            .middleCategoryCode(middleCategory1.getCategoryCode()) // 중 스탠리 스테인리스
                            .product(product2) // 스탠리 스테인리스 텀블러
                            .build()
            );


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
                            .productId(product1.getId()) // 스탠리 텀블러 상품 연결
                            .build()
            );
            exhibitionProductRepository.save(
                    ExhibitionProduct.builder()
                            .exhibition(stanExhibition) // 스탠리 기획전
                            .productId(product2.getId()) // 스탠리 스테인리스 텀블러 상품 연결
                            .build()
            );
            exhibitionProductRepository.save(
                    ExhibitionProduct.builder()
                            .exhibition(summerExhibition) // 여름 기획전
                            .productId(product3.getId()) // 스탠리 고급 스테인리스 텀블러 상품 연결
                            .build()
            );
            exhibitionProductRepository.save( // 중간 테이블 중복 데이터 생성
                    ExhibitionProduct.builder()
                            .exhibition(summerExhibition) // 여름 기획전
                            .productId(product1.getId()) // 스탠리 고급 스테인리스 텀블러 상품 연결
                            .build()
            );
            /**
             * 회원 테스트 데이터
             */
            Member member1 = Member.builder()
                    .loginID("testMemberId1")
                    .name("테스트유저1")
                    .nickname("테스트유저닉네임1")
                    .birthdate(LocalDate.of(1998,1,1))
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
                    .birthdate(LocalDate.of(1998,1,2))
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
                    .productId(product1_option2.getProduct().getId())
                    .productOption(product1_option1) // 스탠리 텀블러 옵션1
                    .qty(2)
                    .isChecked(false)
                    .isDeleted(false)
                    .build()
            );
            cartRepository.save(Cart.builder()
                    .userUid(member1.getMemberUID())
                    .productId(product1_option2.getProduct().getId())
                    .productOption(product1_option2) // 스탠리 텀블러 옵션2
                    .qty(4)
                    .isChecked(false)
                    .isDeleted(false)
                    .build()
            );

            cartRepository.save(Cart.builder()
                    .userUid(member1.getMemberUID())
                    .productId(product4_option1.getProduct().getId()) //
                    .productOption(product4_option1) // 펭귄컵 옵션1
                    .qty(4)
                    .isChecked(false)
                    .isDeleted(false)
                    .build()
            );




        };
    }
}