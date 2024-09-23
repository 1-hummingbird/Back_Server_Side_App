package com.hummingbird.kr.starbuckslike.batch.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Log4j2
@Configuration
public class ProcessingTimeListener {
    @Bean
    public JobExecutionListener jobExecutionListener() {
        return new JobExecutionListener() {

            private LocalDateTime startTime;

            @Override
            public void beforeJob(JobExecution jobExecution) {
                startTime = LocalDateTime.now(); // 작업 시작 시간 기록
                log.info("Job 시작 시간: {}", startTime);
            }

            @Override
            public void afterJob(JobExecution jobExecution) {
                LocalDateTime endTime = LocalDateTime.now(); // 작업 종료 시간 기록
                long nanos = ChronoUnit.NANOS.between(startTime, endTime);
                double seconds = nanos / 1_000_000_000.0; // 초 단위로 변환
                log.info("Job 종료 시간: {}", endTime);
                log.info("Job 실행 시간: {} 초", seconds);
            }
        };
    }
}
