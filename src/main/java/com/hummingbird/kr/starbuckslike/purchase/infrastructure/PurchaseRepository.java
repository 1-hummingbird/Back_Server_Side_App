package com.hummingbird.kr.starbuckslike.purchase.infrastructure;

import com.hummingbird.kr.starbuckslike.purchase.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase,Long> {
}
