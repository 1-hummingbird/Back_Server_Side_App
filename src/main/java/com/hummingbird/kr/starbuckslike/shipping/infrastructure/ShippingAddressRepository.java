package com.hummingbird.kr.starbuckslike.shipping.infrastructure;

import com.hummingbird.kr.starbuckslike.shipping.domain.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Long> {
    
    List<ShippingAddress> findByMemberUID(String memberUID);

    @Modifying
    @Query("DELETE FROM ShippingAddress sa WHERE sa.id = :addressID")
    void deleteByAddressID(@Param("addressID") Long addressID);
}
