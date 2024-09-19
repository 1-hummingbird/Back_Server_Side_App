package com.hummingbird.kr.starbuckslike.review.infrastructure.search;

import jakarta.persistence.EntityManager;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
@Log4j2
class ReviewSearchTest {
    @Autowired
    ReviewSearch reviewSearch;
    @Autowired
    EntityManager em;

    @Test
    void reviewCommentOrderByTest(){
        em.clear();  // JPA 1차 캐시 초기화
        reviewSearch.findReviewCommentByIdTest(1L); // order by 141,174,147
    }
    @Test
    void reviewCommentStreamSortTest(){
        em.clear();  // JPA 1차 캐시 초기화
        reviewSearch.findReviewCommentById(1L); // stream sort 136,139, 151
    }
}