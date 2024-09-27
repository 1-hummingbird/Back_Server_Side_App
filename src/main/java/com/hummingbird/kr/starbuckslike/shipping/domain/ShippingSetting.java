package com.hummingbird.kr.starbuckslike.shipping.domain;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
public class ShippingSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String memberUID;
    
    @Column(nullable = true)
    private Long defaultShippingAddressID;
     
    public ShippingSetting(String memberUID, Long defaultShippingAddressID) {
        this.memberUID = memberUID;
        this.defaultShippingAddressID = defaultShippingAddressID;
    }



    public void setDefaultShippingAddressID(Long defaultShippingAddressID) {
        this.defaultShippingAddressID = defaultShippingAddressID;
    }
}
