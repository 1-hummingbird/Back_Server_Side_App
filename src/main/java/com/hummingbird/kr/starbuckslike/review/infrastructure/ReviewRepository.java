package com.hummingbird.kr.starbuckslike.review.infrastructure;

import com.hummingbird.kr.starbuckslike.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
