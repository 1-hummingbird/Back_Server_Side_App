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