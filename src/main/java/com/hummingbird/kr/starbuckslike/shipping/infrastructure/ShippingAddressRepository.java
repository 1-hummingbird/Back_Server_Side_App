package com.hummingbird.kr.starbuckslike.shipping.infrastructure;

import com.hummingbird.kr.starbuckslike.shipping.domain.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Long> {
    Optional<ShippingAddress> findByUserUuid(String userUuid);
}
