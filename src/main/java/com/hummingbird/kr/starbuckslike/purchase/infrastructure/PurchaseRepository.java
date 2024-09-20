package com.hummingbird.kr.starbuckslike.purchase.infrastructure;

import com.hummingbird.kr.starbuckslike.purchase.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase,Long> {

    // 주문 삭제
    @Modifying
    @Query("UPDATE Purchase p SET p.isDelete = true WHERE p.code = :purchaseCode")
    void softDeletePurchase(@Param("purchaseCode") String purchaseCode);


    // 주문 코드로 조회
    Optional<Purchase> findByCode(String code);

    boolean existsByCode(String code);
}
