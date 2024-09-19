package com.hummingbird.kr.starbuckslike.purchase.infrastructure;

import com.hummingbird.kr.starbuckslike.purchase.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PurchaseRepository extends JpaRepository<Purchase,Long> {

    // 주문 삭제
    @Modifying
    @Query("UPDATE Purchase p SET p.isDelete = true WHERE p.id = :purchaseId")
    void softDeletePurchase(@Param("purchaseId") Long purchaseId);

    boolean existsByCode(String code);
}
