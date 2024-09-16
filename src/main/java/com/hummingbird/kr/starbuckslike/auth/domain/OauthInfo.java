package com.hummingbird.kr.starbuckslike.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import jakarta.persistence.*;

@Entity
@Table(name = "oauth_info")
@Getter
@AllArgsConstructor
public class OauthInfo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String oauthID;
    private String oauthType;
    private String MemberUID;
}
