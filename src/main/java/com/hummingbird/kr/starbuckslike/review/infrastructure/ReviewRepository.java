package com.hummingbird.kr.starbuckslike.review.infrastructure;

import com.hummingbird.kr.starbuckslike.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    // 리뷰 삭제
    @Modifying
    @Query("UPDATE Review r SET r.isDeleted = true WHERE r.id = :reviewId")
    void softDeleteReview(@Param("reviewId") Long reviewId);
}
