package com.hummingbird.kr.starbuckslike.batch;

import com.hummingbird.kr.starbuckslike.batch.dto.WishCountDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@Log4j2
// 100만건 (상품3개) : 0.32초, 0.31초, 0.29초
public class ProductWishJdbcBatch {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final DataSource dataSource; // 데이터 소스 추가
    private final JobExecutionListener jobExecutionListener;


    @Bean
    public Job productWishJdbcJob() {
        return new JobBuilder("productWishJdbcJob", jobRepository)
                .listener(jobExecutionListener) // 처리시간 측정
                .start(productWishJdbcStep())
                .build();
    }

    @Bean
    public Step productWishJdbcStep() {
        return new StepBuilder("productWishJdbcStep", jobRepository)
                .<WishCountDto, WishCountDto>chunk(10, platformTransactionManager)
                .reader(wishJdbcReader()) // 상품에 좋아요 활성화 된 것만 조회
                .processor(wishProcessor())
                .writer(wishJdbcWriter())
                .build();
    }

    @Bean
    public JdbcPagingItemReader<WishCountDto> wishJdbcReader() {
        return new JdbcPagingItemReaderBuilder<WishCountDto>()
                .name("wishJdbcReader")
                .dataSource(dataSource)
                .selectClause("SELECT product_id, COUNT(*) as wish_count")
                .fromClause("FROM wish")
                .whereClause("WHERE is_wished = true")
                .groupClause("GROUP BY product_id")
                .sortKeys(Map.of("product_id", Order.ASCENDING)) // 상품 id 오름차순
                .rowMapper((rs, rowNum) -> {
                    WishCountDto dto = new WishCountDto();
                    dto.setProductId(rs.getLong("product_id"));
                    dto.setWishCount(rs.getLong("wish_count"));
                    return dto;
                })
                .pageSize(10)
                .build();
    }

    @Bean(name = "jdbcWishProcessor")
    public ItemProcessor<WishCountDto, WishCountDto> wishProcessor() {
        return wishCountDto -> wishCountDto; // 딱히 처리 X , 생략해도 무방함
    }

    @Bean
    public JdbcBatchItemWriter<WishCountDto> wishJdbcWriter() {
        return new JdbcBatchItemWriterBuilder<WishCountDto>()
                .dataSource(dataSource)
                .sql("INSERT INTO wish_product (product_id, wish_count, created_at, updated_at) " +
                        "VALUES (:productId, :wishCount, SYSDATE(), SYSDATE()) " +
                        // 상품 id 중복 시 insert 않고  업데이트 수행 (통계성 데이터를 만들 계획이 아니므로 필요 X)
                        "ON DUPLICATE KEY UPDATE wish_count = :wishCount, updated_at = SYSDATE()")
                // WishCountDto의 필드 값 파라미터에 매핑
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .build();
    }
}