package com.hummingbird.kr.starbuckslike.purchase.infrastructure;

import com.hummingbird.kr.starbuckslike.product.domain.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishRepository extends JpaRepository<Wish,Long> {
    Optional<Wish> findWishByMemberUidAndProductId(String memberUid, Long productId);

}
