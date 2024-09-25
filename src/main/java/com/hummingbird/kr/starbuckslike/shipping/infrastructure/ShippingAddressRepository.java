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

    @Query(value = "SELECT sa.id from shipping_address sa where sa.address_nickname =:nickname and sa.name = :name and sa.address = :address and sa.Phone = :phone and sa.memberUID = :memberUID and sa.memo = :memo ORDER BY sa.id LIMIT 1", nativeQuery = true)
    Long findIdByAllArgument(@Param("nickname") String addressNickname,
                           @Param("name") String name,
                           @Param("address") String address,
                           @Param("phone") String phone,
                           @Param("memberUID") String memberUID,
                           @Param("memo") String memo);
}
