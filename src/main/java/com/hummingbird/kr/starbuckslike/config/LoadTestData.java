package com.hummingbird.kr.starbuckslike.config;


import com.hummingbird.kr.starbuckslike.common.utils.DateLocalDateConverter;
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
import com.hummingbird.kr.starbuckslike.purchase.domain.Purchase;
import com.hummingbird.kr.starbuckslike.purchase.domain.PurchaseProduct;
import com.hummingbird.kr.starbuckslike.purchase.domain.PurchaseStatus;
import com.hummingbird.kr.starbuckslike.purchase.infrastructure.PurchaseProductRepository;
import com.hummingbird.kr.starbuckslike.purchase.infrastructure.PurchaseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.time.LocalDate;
import java.util.UUID;

import static com.hummingbird.kr.starbuckslike.common.utils.DateLocalDateConverter.localDateToDate;

/**
 * 테스트용 데이터
 * yml 파일 ddl-auto: create 되어 있어야 함
 * @author 허정현
 */
@Configuration
@ComponentScan(basePackages = "com.hummingbird.kr.starbuckslike")
public class LoadTestData {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
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
            BannerRepository bannerRepository,
            PurchaseRepository purchaseRepository,
            PurchaseProductRepository purchaseProductRepository
                                   ) {
        return args -> {
            /**
             * 테스트용 임시 카테고리 데이터 추가
             */
            // 상 카테고리
            TopCategory topCategory1 = TopCategory.builder()
                    .categoryName("대 텀블러/보온병")
                    .categoryDescription("대 텀블러/보온병 카테고리 입니다")
                    .categoryCode(CategoryCodeGenerator.generateCategoryCode("tc-"))
                    .imageUrl("https://image.istarbucks.co.kr/upload/store/skuimg/2022/02/[9300000003591]_20220222165515109.jpg")
                    .build();
            topCategoryRepository.save(topCategory1);
            TopCategory topCategory2 = TopCategory.builder()
                    .categoryName("대 머그/컵")
                    .categoryDescription("대 머그/컵 카테고리 입니다")
                    .categoryCode(CategoryCodeGenerator.generateCategoryCode("tc-"))
                    .imageUrl("https://image.istarbucks.co.kr/upload/store/skuimg/2023/04/[9300000004628]_20230425125117122.jpg")
                    .build();
            topCategoryRepository.save(topCategory2);
            TopCategory topCategory3 = TopCategory.builder()
                    .categoryName("대 라이프스타일")
                    .categoryDescription("대 라이프스타일 카테고리 입니다")
                    .categoryCode(CategoryCodeGenerator.generateCategoryCode("tc-"))
                    .imageUrl("https://image.istarbucks.co.kr/upload/store/skuimg/2023/09/[9300000004606]_20230905081551720.jpg")
                    .build();
            topCategoryRepository.save(topCategory3);
            TopCategory topCategory4 = TopCategory.builder()
                    .categoryName("대 티/커피용품")
                    .categoryDescription("대 티/커피용품 카테고리 입니다")
                    .categoryCode(CategoryCodeGenerator.generateCategoryCode("tc-"))
                    .imageUrl("https://image.istarbucks.co.kr/upload/store/skuimg/2021/06/[9300000000393]_20210618110316466.jpg")
                    .build();
            topCategoryRepository.save(topCategory4);
            TopCategory topCategory5 = TopCategory.builder()
                    .categoryName("대 케이크")
                    .categoryDescription("대 케이크 카테고리 입니다")
                    .categoryCode(CategoryCodeGenerator.generateCategoryCode("tc-"))
                    .imageUrl("https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[9300000000132]_20210422112714121.jpg")
                    .build();
            topCategoryRepository.save(topCategory5);
            // 중 카테고리
            MiddleCategory top1_middle1 = MiddleCategory.builder()
                    .categoryName("중 플라스틱 텀블러")
                    .categoryDescription("중 플라스틱 텀블러 카테고리 입니다")
                    .categoryCode(CategoryCodeGenerator.generateCategoryCode("bc-"))
                    .topCategory(topCategory1) //대 텀블러/보온병
                    .build();
            middleCategoryRepository.save(top1_middle1);
            MiddleCategory top1_middle2 = MiddleCategory.builder()
                    .categoryName("중 스테인리스 텀블러")
                    .categoryDescription("중 스테인리스 텀블러 카테고리 입니다")
                    .categoryCode(CategoryCodeGenerator.generateCategoryCode("bc-"))
                    .topCategory(topCategory1) //대 텀블러/보온병
                    .build();
            middleCategoryRepository.save(top1_middle2);
            MiddleCategory top1_middle3 = MiddleCategory.builder()
                    .categoryName("중 보온병")
                    .categoryDescription("중 보온병 카테고리 입니다")
                    .categoryCode(CategoryCodeGenerator.generateCategoryCode("bc-"))
                    .topCategory(topCategory1) //대 텀블러/보온병
                    .build();
            middleCategoryRepository.save(top1_middle3);
            MiddleCategory top1_middle4 = MiddleCategory.builder()
                    .categoryName("중 콜드컵")
                    .categoryDescription("중 콜드컵 카테고리 입니다")
                    .categoryCode(CategoryCodeGenerator.generateCategoryCode("bc-"))
                    .topCategory(topCategory1) //대 텀블러/보온병
                    .build();
            middleCategoryRepository.save(top1_middle4);
            MiddleCategory top2_middle1 = MiddleCategory.builder()
                    .categoryName("중 머그")
                    .categoryDescription("중 머그 카테고리 입니다")
                    .categoryCode(CategoryCodeGenerator.generateCategoryCode("bc-"))
                    .topCategory(topCategory2) //대 머그/컵
                    .build();
            middleCategoryRepository.save(top2_middle1);
            MiddleCategory top2_middle2 = MiddleCategory.builder()
                    .categoryName("중 글라스")
                    .categoryDescription("중 글라스 카테고리 입니다")
                    .categoryCode(CategoryCodeGenerator.generateCategoryCode("bc-"))
                    .topCategory(topCategory2) //대 머그/컵
                    .build();
            middleCategoryRepository.save(top2_middle2);
            // 상 카테고리 [라이프스타일, 티/커피용품] 건너뜀
            MiddleCategory top5_middle1 = MiddleCategory.builder()
                    .categoryName("중 롤케이크")
                    .categoryDescription("중 롤케이크 카테고리 입니다")
                    .categoryCode(CategoryCodeGenerator.generateCategoryCode("bc-"))
                    .topCategory(topCategory5) // 대 케이크
                    .build();
            middleCategoryRepository.save(top5_middle1);
            MiddleCategory top5_middle2 = MiddleCategory.builder()
                    .categoryName("중 홀케이크")
                    .categoryDescription("중 홀케이크 카테고리 입니다")
                    .categoryCode(CategoryCodeGenerator.generateCategoryCode("bc-"))
                    .topCategory(topCategory5) // 대 케이크
                    .build();
            middleCategoryRepository.save(top5_middle2);

            // 하 카테고리

            /**
             * 테스트용 임시 상품 데이터 추가
             */
            Product top1_middle1_product1 = productRepository.save(Product.builder()
                    .name("크레이브 하우스 워터보틀 500ml")
                    .price(30000)
                    .isDiscounted(false)
                    .discountRate(0.0f)
                    .shortDescription("크레이브 하우스 워터보틀")
                    .fullDescription("<p>크레이브 하우스 워터보틀 상품 상세</p>")
                    .isAvailable(true)
                    .isHidden(false)
                    .isDeleted(false)
                    .build()
            );

            Product top1_middle2_product2 = productRepository.save(Product.builder()
                    .name("SS 뉴에라 다알라 텀블러 473ml")
                    .price(40000)
                    .isDiscounted(true)
                    .discountRate(10.0f)
                    .shortDescription("SS 뉴에라 다알라 텀블러 473ml 압나더.")
                    .fullDescription("<p>SS 뉴에라 다알라 텀블러 473ml</p>")
                    .isAvailable(true)
                    .isHidden(false)
                    .isDeleted(false)
                    .build()
            );

            Product top2_middle1_product1 =productRepository.save(Product.builder()
                    .name("(c)애니버서리 스케일 머그 89ml")
                    .price(50000)
                    .isDiscounted(true)
                    .discountRate(15.0f)
                    .shortDescription("(c)애니버서리 스케일 머그 89ml 입니다")
                    .fullDescription("<p>(c)애니버서리 스케일 머그 89ml 상품 상세</p>")
                    .isAvailable(true)
                    .isHidden(false)
                    .isDeleted(false)
                    .build()
            );


            // 상품 이미지
            productImageRepository.save(ProductImage.builder()
                    .product(top1_middle1_product1)
                    .imageUrl("https://image.istarbucks.co.kr/upload/store/skuimg/2023/09/[9300000004617]_20230905083145871.jpg")
                    .seq(0)
                    .build()
            );
            productImageRepository.save(ProductImage.builder()
                    .product(top1_middle1_product1)
                    .imageUrl("https://image.istarbucks.co.kr/upload/store/skuimg/2023/09/[9300000004617]_20230905083215032.jpg")
                    .seq(1)
                    .build()
            );
            productImageRepository.save(ProductImage.builder()
                    .product(top1_middle1_product1)
                    .imageUrl("https://image.istarbucks.co.kr/upload/store/skuimg/2023/09/[9300000004617]_20230905083229280.jpg")
                    .seq(2)
                    .build()
            );


            // 상품 옵션
            ProductOption top1_middle1_product1_op1 = ProductOption.builder()
                    .product(top1_middle1_product1) // 크레이브 하우스 워터보틀 500ml 옵션
                    .name("크레이브 하우스 워터보틀 500ml 옵션1")
                    .price(30000)
                    .quantity(20000L)
                    .isInputOption(false)
                    .discountRate(0.0f)
                    .isAvailable(true)
                    .isHidden(false)
                    .isDeleted(false)
                    .build();
            productOptionRepository.save(top1_middle1_product1_op1);
            ProductOption top1_middle1_product1_op2 = ProductOption.builder()
                    .product(top1_middle1_product1) // 크레이브 하우스 워터보틀 500ml 옵션
                    .name("크레이브 하우스 워터보틀 500ml 옵션2")
                    .price(29000)
                    .quantity(10000L)
                    .isInputOption(false)
                    .discountRate(0.0f)
                    .isAvailable(true)
                    .isHidden(false)
                    .isDeleted(false)
                    .build();
            productOptionRepository.save(top1_middle1_product1_op2);
            ProductOption top1_middle2_product2_op1 = ProductOption.builder()
                    .product(top1_middle2_product2) // SS 뉴에라 다알라 텀블러 473ml 옵션
                    .name("SS 뉴에라 다알라 텀블러 473ml 옵션1")
                    .price(29000)
                    .quantity(10000L)
                    .isInputOption(false)
                    .discountRate(0.0f)
                    .isAvailable(true)
                    .isHidden(false)
                    .isDeleted(false)
                    .build();
            productOptionRepository.save(top1_middle2_product2_op1);
            ProductOption top2_middle1_product1_op1 = ProductOption.builder()
                    .product(top2_middle1_product1) // (c)애니버서리 스케일 머그 89ml
                    .name("(c)애니버서리 스케일 머그 89ml 옵션1")
                    .price(29000)
                    .quantity(15000L)
                    .isInputOption(false)
                    .discountRate(0.0f)
                    .isAvailable(true)
                    .isHidden(false)
                    .isDeleted(false)
                    .build();
            productOptionRepository.save(top2_middle1_product1_op1);



            /**
             * 카테고리 <-> 상품 중간테이블
             */
            //
            categoryProductListRepository.save(
                    CategoryProductList.builder()
                            .topCategoryCode(topCategory1.getCategoryCode()) // 대 텀블러/보온병
                            .middleCategoryCode(top1_middle1.getCategoryCode()) // 중 플라스틱 텀블러
                            .product(top1_middle1_product1) // 크레이브 하우스 워터보틀 500ml
                            .build()
            );
            categoryProductListRepository.save(
                    CategoryProductList.builder()
                            .topCategoryCode(topCategory1.getCategoryCode()) // 대 텀블러/보온병
                            .middleCategoryCode(top1_middle2.getCategoryCode()) // 중 스테인리스 텀블러
                            .product(top1_middle2_product2) // SS 뉴에라 다알라 텀블러 473ml
                            .build()
            );
            categoryProductListRepository.save(
                    CategoryProductList.builder()
                            .topCategoryCode(topCategory2.getCategoryCode()) // 대 텀블러/보온병
                            .middleCategoryCode(top2_middle1.getCategoryCode()) // 중 머그
                            .product(top2_middle1_product1) // (c)애니버서리 스케일 머그 89ml
                            .build()
            );
//            categoryProductListRepository.save(
//                    CategoryProductList.builder()
//                            .topCategoryCode(topCategory5.getCategoryCode()) // 대 케이크
//                            .middleCategoryCode(top5_middle1.getCategoryCode()) // 중 롤 케이크
//                            .product(top1_middle1_product1) // 크레이브 하우스 워터보틀 500ml
//                            .build()
//            );

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
            Exhibition tumblerExhibition = exhibitionRepository.save(
                    Exhibition.builder()
                            .name("텀블러 기획전")
                            .title("텀블러 기획전 타이틀")
                            .fullDescription("<p>텀블러 기획전 상세</p>")
                            .startDate(LocalDate.of(2024,7,1))
                            .endDate(LocalDate.of(2024,12,25))
                            .isDeleted(false)
                            .build()
            );
            // 기획전 , 상품 중간 테이블 데이터
            exhibitionProductRepository.save(
                    ExhibitionProduct.builder()
                            .exhibition(summerExhibition) // 여름 기획전
                            .productId(top1_middle1_product1.getId()) // 크레이브 하우스 워터보틀 500ml
                            .build()
            );
            exhibitionProductRepository.save(
                    ExhibitionProduct.builder()
                            .exhibition(tumblerExhibition) // 텀블러 기획전
                            .productId(top1_middle2_product2.getId()) // SS 뉴에라 다알라 텀블러 473ml 상품 연결
                            .build()
            );
            exhibitionProductRepository.save(
                    ExhibitionProduct.builder()
                            .exhibition(summerExhibition) // 여름 기획전
                            .productId(top1_middle2_product2.getId()) // SS 뉴에라 다알라 텀블러 473ml
                            .build()
            );

            /**
             * 회원 테스트 데이터
             */
            Member member1 = Member.builder()
                    .loginID("testMemberId1")
                    .name("테스트유저1")
                    .nickname("테스트유저닉네임1")
                    .birthdate(localDateToDate(LocalDate.of(1998,1,1)))
                    .phone("010-1234-5678")
                    .email("test@test.com")
                    .password(passwordEncoder.encode( "test1234"))
                    .memberUID(UUID.randomUUID().toString())
                    .build();
            memberRepository.save(member1);
            Member member2 = Member.builder()
                    .loginID("testMemberId2")
                    .name("테스트유저2")
                    .nickname("테스트유저닉네임2")
                    .birthdate(localDateToDate(LocalDate.of(1998,1,2)))
                    .phone("010-1234-5678")
                    .email("test@test.com")
                    .password(passwordEncoder.encode( "test"))
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
                    .productId(top1_middle1_product1.getId())
                    .productOption(top1_middle1_product1_op1) // 크레이브 하우스 워터보틀 500ml 옵션1
                    .qty(2)
                    .isChecked(false)
                    .isDeleted(false)
                    .build()
            );
            cartRepository.save(Cart.builder()
                    .userUid(member1.getMemberUID())
                    .productId(top1_middle1_product1.getId())
                    .productOption(top1_middle1_product1_op2) // 크레이브 하우스 워터보틀 500ml 옵션2
                    .qty(2)
                    .isChecked(false)
                    .isDeleted(false)
                    .build()
            );
            cartRepository.save(Cart.builder()
                    .userUid(member1.getMemberUID())
                    .productId(top1_middle2_product2.getId())
                    .productOption(top1_middle2_product2_op1) //SS 뉴에라 다알라 텀블러 473ml 옵션1
                    .qty(2)
                    .isChecked(false)
                    .isDeleted(false)
                    .build()
            );

            /**
             * 주문, 주문상품
             */

            Purchase purchase1 = purchaseRepository.save(
                    Purchase.builder()
                            .address("주소 주소 주소")
                            .primaryPhone(member1.getPhone())
                            .userName(member1.getName())
                            .userUuid(member1.getMemberUID())
                            .memo("현관문 앞에 배송해주세요")
                            .build()
            );
            purchaseProductRepository.save(
                    PurchaseProduct.builder()
                            .purchase(purchase1)
                            .qty(2)
                            .price( (long) (top1_middle1_product1_op1.getPrice()* 2) )
                            .discountPrice(2000L)
                            //.inputData()
                            .optionId(top1_middle1_product1_op1.getId())
                            .optionName(top1_middle1_product1_op1.getName()) //크레이브 하우스 워터보틀 500ml 옵션1
                            .productId(top1_middle1_product1_op1.getProduct().getId())
                            .productName(top1_middle1_product1_op1.getProduct().getName())
                            .purchaseStatus(PurchaseStatus.PENDING)
//                            .isShipped(false)
//                            .isDelivered(false)
//                            .isConfirmed(false)
                            .build()
            );
            purchaseProductRepository.save(
                    PurchaseProduct.builder()
                            .purchase(purchase1)
                            .qty(3)
                            .price((long) (top2_middle1_product1_op1.getPrice()* 3) )
                            .discountPrice(1000L)
                            //.inputData()
                            .optionId(top2_middle1_product1_op1.getId())
                            .optionName(top2_middle1_product1_op1.getName()) // 애니버서리 스케일 머그 89ml 옵션1
                            .productId(top2_middle1_product1_op1.getProduct().getId())
                            .productName(top2_middle1_product1_op1.getProduct().getName())
                            .purchaseStatus(PurchaseStatus.PENDING)
//                            .isShipped(false)
//                            .isDelivered(false)
//                            .isConfirmed(false)
                            .build()
            );
            Purchase purchase2 = purchaseRepository.save(
                    Purchase.builder()
                            .address("주소 주소 주소2")
                            .primaryPhone(member1.getPhone())
                            .userName(member1.getName())
                            .userUuid(member1.getMemberUID())
                            .memo("현관문 앞에 배송해주세요2")
                            .build()
            );
            purchaseProductRepository.save(
                    PurchaseProduct.builder()
                            .purchase(purchase2)
                            .qty(4)
                            .price((long) (top1_middle1_product1_op2.getPrice() * 4))
                            .discountPrice(3000L)
                            //.inputData()
                            .optionId(top1_middle1_product1_op2.getId())
                            .optionName(top1_middle1_product1_op2.getName()) //크레이브 하우스 워터보틀 500ml 옵션1
                            .productId(top1_middle1_product1_op2.getProduct().getId())
                            .productName(top1_middle1_product1_op2.getProduct().getName())
                            .purchaseStatus(PurchaseStatus.PENDING)

                            .build()
            );



        };
    }
}