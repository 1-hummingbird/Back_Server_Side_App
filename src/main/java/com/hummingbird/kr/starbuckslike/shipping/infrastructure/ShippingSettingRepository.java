package com.hummingbird.kr.starbuckslike.shipping.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hummingbird.kr.starbuckslike.shipping.domain.ShippingSetting;

public interface ShippingSettingRepository extends JpaRepository<ShippingSetting, Long>{

}
