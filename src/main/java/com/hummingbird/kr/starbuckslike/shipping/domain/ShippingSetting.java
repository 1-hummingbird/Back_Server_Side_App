package com.hummingbird.kr.starbuckslike.shipping.domain;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
public class ShippingSetting {
    private Long id;
    private String memberUID;
    private String defaultShippingAddress;
    private Integer countShipping;
}
