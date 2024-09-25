package com.hummingbird.kr.starbuckslike.batch;

import com.hummingbird.kr.starbuckslike.batch.dto.WishCountDto;
import com.hummingbird.kr.starbuckslike.batch.entity.WishProduct;
import com.hummingbird.kr.starbuckslike.batch.repository.WishProductRepository;
import com.hummingbird.kr.starbuckslike.product.infrastructure.WishRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Map;

/**
 * group by X
 */
// 1만건 : 10초 , 8.19초
// 5만건 : 116초
//@Configuration
//@RequiredArgsConstructor
//@Log4j2
//public class ProductWishJpaBatch {
//
//    private final JobRepository jobRepository;
//    private final PlatformTransactionManager platformTransactionManager;
//    private final WishRepository wishRepository;
//    private final WishProductRepository wishProductRepository;
//    private final JobExecutionListener jobExecutionListener;
//
//    // 배치 작업 정의
//    @Bean
//    public Job productWishJpaJob() {
//        return new JobBuilder("productWishJpaJob", jobRepository)
//                .listener(jobExecutionListener) // 처리시간 측정
//                .start(productWishJpaStep())
//                .build();
//    }
//
//    @Bean
//    public Step productWishJpaStep() {
//        return new StepBuilder("productWishJpaStep", jobRepository)
//                .<Wish, Wish>chunk(10, platformTransactionManager)
//                .reader(wishReader())
//                .processor(wishProcessor())
//                .writer(wishWriter())
//                .build();
//    }
//
//    @Bean
//    public RepositoryItemReader<Wish> wishReader() {
//        return new RepositoryItemReaderBuilder<Wish>()
//                .name("wishReader")
//                .repository(wishRepository)
//                .methodName("findByIsWishedTrue") //
//                .pageSize(10)
//                .sorts(Map.of("id", Sort.Direction.ASC))
//                .build();
//    }
//
//    @Bean
//    public ItemProcessor<Wish, Wish> wishProcessor() {
//        return wish -> wish;
//    }
//
//    @Bean
//    public ItemWriter<Wish> wishWriter() {
//        return items -> {
//                long startTime = System.currentTimeMillis();
//            // 모든 상품의 좋아요 개수 초기화
//            Set<Long> productIds = new HashSet<>();
//            for (Wish wish : items) {
//                productIds.add(wish.getProductId());
//            }
//
//            // WishProduct wish_count를 0으로 초기화
//            for (Long productId : productIds) {
//                WishProduct existingStat = wishProductRepository.findByProductId(productId).orElse(null);
//                if (existingStat != null) {
//                    existingStat.updateWishCount(0L);
//                    wishProductRepository.save(existingStat);
//                }
//            }
//
//            // Wish  상품별로 집계하여 wishCount를 계산
//            Map<Long, Long> wishCountMap = new HashMap<>();
//
//            for (Wish wish : items) {
//                Long productId = wish.getProductId();
//                wishCountMap.put(productId, wishCountMap.getOrDefault(productId, 0L) + 1);
//            }
//
//            // 집계된 결과  WishProduct 테이블에 저장
//            for (Map.Entry<Long, Long> entry : wishCountMap.entrySet()) {
//                Long productId = entry.getKey();
//                Long totalWishCount = entry.getValue();
//
//                WishProduct existingStat = wishProductRepository.findByProductId(productId).orElse(null);
//
//                if (existingStat != null) {
//                    // 기존 데이터가 존재하면 좋아요 수 업데이트
//                    existingStat.updateWishCount(totalWishCount);
//                    wishProductRepository.save(existingStat);
//                } else {
//                    // 데이터가 없으면 새로 생성
//                    WishProduct newStat = WishProduct.builder()
//                            .productId(productId)
//                            .wishCount(totalWishCount)
//                            .build();
//                    wishProductRepository.save(newStat);
//                }
//            }
//        };
//    }
//}




/**
 * group by O
 */
// 4만건 기준 523ms
@Configuration
@RequiredArgsConstructor
@Log4j2
public class ProductWishJpaBatch {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final WishRepository wishRepository;
    private final WishProductRepository wishProductRepository;
    private final JobExecutionListener jobExecutionListener;


    @Bean
    public Job productWishJpaJob() {
        return new JobBuilder("productWishJpaJob", jobRepository)
                .listener(jobExecutionListener) // 처리시간 측정
                .start(productWishJpaStep())
                .build();
    }

    @Bean
    public Step productWishJpaStep() {
        return new StepBuilder("productWishJpaStep", jobRepository)
                .<WishCountDto, WishCountDto>chunk(10, platformTransactionManager)
                .reader(wishReader())     // 좋아요 활성화된 데이터만 조회
                .processor(wishProcessor())
                .writer(wishWriter())
                .build();
    }

    @Bean
    public RepositoryItemReader<WishCountDto> wishReader() {
        return new RepositoryItemReaderBuilder<WishCountDto>()
                .name("wishReader")
                .repository(wishRepository)
                .methodName("findWishCountGroupedByProductId")
                .pageSize(10)
                .sorts(Map.of("productId", Sort.Direction.ASC))
                .build();
    }

    @Bean(name = "jpaWishProcessor")
    public ItemProcessor<WishCountDto, WishCountDto> wishProcessor() {
        return wishCountDto -> wishCountDto; // 딱히 처리 X , 생략해도 무방함
    }

    @Bean
    public ItemWriter<WishCountDto> wishWriter() {
        return items -> {
            for (WishCountDto dto : items) {
                Long productId = dto.getProductId();
                Long totalWishCount = dto.getWishCount();
                // 통계 테이블 확인
                WishProduct existWish = wishProductRepository.findByProductId(productId).orElse(null);
                if (existWish != null) {
                    // 기존 데이터가 존재하면 좋아요 수 업데이트
                    existWish.updateWishCount(totalWishCount);
                    wishProductRepository.save(existWish);
                } else {
                    // 데이터가 없으면 새로 생성
                    WishProduct newStat = WishProduct.builder()
                            .productId(productId)
                            .wishCount(totalWishCount)
                            .build();
                    wishProductRepository.save(newStat);
                }
            }
        };
    }


}