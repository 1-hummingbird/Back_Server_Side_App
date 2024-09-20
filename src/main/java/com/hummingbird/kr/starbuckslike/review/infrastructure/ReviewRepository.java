package com.hummingbird.kr.starbuckslike.review.infrastructure;

import com.hummingbird.kr.starbuckslike.review.domain.Review;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    // 리뷰 삭제
    @Modifying
    @Query("UPDATE Review r SET r.isDeleted = true WHERE r.id = :reviewId")
    void softDeleteReview(@Param("reviewId") Long reviewId);


    // 비관적 락을 사용하여 리뷰 조회
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT r FROM Review r WHERE r.id = :id")
    Review findByIdWithPessimisticLock(@Param("id") Long id);
}
