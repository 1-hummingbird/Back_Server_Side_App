package com.hummingbird.kr.starbuckslike.shipping.domain;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor
public class ShippingSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String memberUID;
    
    @Column(nullable = true)
    private Long defaultShippingAddressID;
    
    private int countShipping;

    public ShippingSetting(String memberUID, int countShipping) {
        this.memberUID = memberUID;
        this.countShipping = countShipping;
    }

    public void countShippingPlus(){
        this.countShipping++;
    }
    public void countShippingMinus(){
        this.countShipping--;
    }

    public void setDefaultShippingAddressID(Long defaultShippingAddressID) {
        this.defaultShippingAddressID = defaultShippingAddressID;
    }
}
