package com.hummingbird.kr.starbuckslike.delivery.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class DeliveryDto {
    //DTD는 사용자의 요청이나 응답이 필요한

    private Long id;
    private String alias;
    private String name;
    private String address;
    private int phone;
    private String userid;

    //   private String basic_flag;

    //기본생성자
    public DeliveryDto(){

    }



    // 왜 이렇게 코드를 짰는지 이해를 못하고있음.
    //
    public DeliveryDto(Long id,
                       String alias,
                       String name,
                       String address,
                       int phone,
                       String userid) {
        this.id = id;
        this.alias = alias;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.userid = userid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
