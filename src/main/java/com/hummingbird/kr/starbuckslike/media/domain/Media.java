package com.hummingbird.kr.starbuckslike.media.domain;

import com.hummingbird.kr.starbuckslike.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter //Lombok 라이브러리에서 제공하는 어노테이션,
@Entity //해당 클래스가 데이터베이스의 테이블과 매핑된다는 것 즉 Meida 클래스가 데이테베이스의 테이블과 매
@NoArgsConstructor //기본 생성자(인자가 없는 생성자)를 자동으로 만들어주는 역할
public class Media extends BaseEntity {

    /*
    @Id 어노테이션을 통해 **Primary Key(기본 키)**로 지정
    @GeneratedValue: 자동으로 값을 생성해주는 어노테이션
    strategy = GenerationType.IDENTITY: "ID를 자동으로 하나씩 증가시켜라" 라는 뜻
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
    nullable = false null 값이 허용되지 않는다는 의미
    문자열은 최대한 200자까지만
    즉 mediaUrl을 200자까지 제한한다는 의미
    */
    @Column(nullable = false, length = 200)
    private String mediaUrl;


    @Column(nullable = false, length = 200)
    private String mediaName;

    @Column(nullable = false, length = 200)
    private String mediaType;

    @Builder
    public Media(Long id, String mediaUrl, String mediaName, String mediaType){
        this.id = id; // this.id는 클래스 필드, id는 메서드 파라미터
        this.mediaName = mediaName;
        this.mediaType = mediaType;
        this.mediaUrl = mediaUrl;
    }
}
