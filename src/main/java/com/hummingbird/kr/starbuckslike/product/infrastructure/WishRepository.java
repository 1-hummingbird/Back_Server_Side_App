package com.hummingbird.kr.starbuckslike.product.infrastructure;

import com.hummingbird.kr.starbuckslike.batch.dto.WishCountDto;
import com.hummingbird.kr.starbuckslike.product.domain.Wish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface WishRepository extends JpaRepository<Wish,Long> {
    Optional<Wish> findWishByMemberUIDAndProductId(String memberUID, Long productId);

    // 상품별 좋아요 수 집계
    @Query("SELECT new com.hummingbird.kr.starbuckslike.batch.dto.WishCountDto(w.productId, COUNT(w)) " +
            "FROM Wish w WHERE w.isWished = true GROUP BY w.productId")
    Page<WishCountDto> findWishCountGroupedByProductId(Pageable pageable); // Pageable 인자 추가



    Page<Wish> findByIsWishedTrue(Pageable pageable);

}
