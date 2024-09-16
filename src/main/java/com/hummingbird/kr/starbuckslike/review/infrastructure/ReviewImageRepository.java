package com.hummingbird.kr.starbuckslike.review.infrastructure;

import com.hummingbird.kr.starbuckslike.review.domain.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewImageRepository extends JpaRepository<ReviewImage,Long> {
}
