package com.hummingbird.kr.starbuckslike.shipping.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hummingbird.kr.starbuckslike.shipping.domain.ShippingSetting;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ShippingSettingRepository extends JpaRepository<ShippingSetting, Long>{

    @Query("SELECT ss FROM ShippingSetting ss WHERE ss.memberUID = :memberUID")
    Optional<ShippingSetting> findByMemberUID(@Param("memberUID") String memberUID);
}
