package com.hummingbird.kr.starbuckslike.purchase.infrastructure;

import com.hummingbird.kr.starbuckslike.purchase.domain.PurchaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PurchaseProductRepository extends JpaRepository<PurchaseProduct,Long> {
    // 주문 상품 리뷰 작성 상태 업데이트
    @Modifying
    @Query("UPDATE PurchaseProduct pp SET pp.isReviewed = true WHERE pp.id = :purchaseProductId")
    void updatePurchaseProductReview(@Param("purchaseProductId") Long purchaseProductId);

}
