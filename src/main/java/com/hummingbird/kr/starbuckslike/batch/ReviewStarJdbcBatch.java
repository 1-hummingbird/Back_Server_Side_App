package com.hummingbird.kr.starbuckslike.batch;

import com.hummingbird.kr.starbuckslike.batch.dto.ReviewStarDto;
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
public class ReviewStarJdbcBatch {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final DataSource dataSource; // 데이터 소스 주입
    private final JobExecutionListener jobExecutionListener;

    // 배치 작업 정의
    @Bean
    public Job productReviewStarJdbcJob() {
        return new JobBuilder("productReviewStarJdbcJob", jobRepository)
                .listener(jobExecutionListener) // 처리 시간 측정 리스너 추가
                .start(productReviewStarJdbcStep())
                .build();
    }

    // Step 정의
    @Bean
    public Step productReviewStarJdbcStep() {
        return new StepBuilder("productReviewStarJdbcStep", jobRepository)
                .<ReviewStarDto, ReviewStarDto>chunk(10, platformTransactionManager) // chunk size 설정
                .reader(reviewStarJdbcReader())
                .processor(reviewStarProcessor())
                .writer(reviewStarJdbcWriter())
                .build();
    }

    @Bean
    public JdbcPagingItemReader<ReviewStarDto> reviewStarJdbcReader() {
        return new JdbcPagingItemReaderBuilder<ReviewStarDto>()
                .name("reviewStarJdbcReader")
                .dataSource(dataSource)
                .selectClause("SELECT product_id, COUNT(*) as review_count, SUM(star) as total_star, " +
                        "AVG(star) as average_star, SUM(CASE WHEN is_photo = true THEN 1 ELSE 0 END) as photo_review_count")
                .fromClause("FROM review")
                .whereClause("WHERE is_deleted = false") // 삭제되지 않은 리뷰만 선택
                .groupClause("GROUP BY product_id")
                .sortKeys(Map.of("product_id", Order.ASCENDING)) // product_id 오름차순 정렬
                .rowMapper((rs, rowNum) -> {
                    ReviewStarDto dto = new ReviewStarDto();
                    dto.setProductId(rs.getLong("product_id"));
                    dto.setReviewCount(rs.getLong("review_count"));
                    dto.setTotalStar(rs.getLong("total_star"));
                    dto.setAverageStar(rs.getDouble("average_star"));
                    dto.setPhotoReviewCount(rs.getLong("photo_review_count"));
                    return dto;
                })
                .pageSize(10) // 페이징 처리, 한번에 읽어올 데이터 개수
                .build();
    }

    @Bean(name = "jdbcReviewStarProcessor")
    public ItemProcessor<ReviewStarDto, ReviewStarDto> reviewStarProcessor() {
        return reviewStarDto -> reviewStarDto; // 딱히 처리 X , 생략해도 무방함
    }

    @Bean
    public JdbcBatchItemWriter<ReviewStarDto> reviewStarJdbcWriter() {
        return new JdbcBatchItemWriterBuilder<ReviewStarDto>()
                .dataSource(dataSource)
                .sql("INSERT INTO review_star (product_id, review_count, average_star, photo_review_count, total_star, created_at, updated_at) " +
                        "VALUES (:productId, :reviewCount, :averageStar, :photoReviewCount, :totalStar, SYSDATE(), SYSDATE()) " +
                        "ON DUPLICATE KEY UPDATE review_count = :reviewCount, " +
                        "average_star = :averageStar , " +
                        "photo_review_count = :photoReviewCount , " +
                        "total_star = :totalStar, updated_at = SYSDATE()")
                // ReviewStarDto의 필드 값 파라미터에 매핑
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .build();
    }
}
