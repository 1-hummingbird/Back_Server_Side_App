package com.hummingbird.kr.starbuckslike.temp.domain;

/**
 * 판매 상태 타입
 * @author 허정현
 * 08/27 제안 : 그냥 이거 DB 테이블에 String으로 넣는거 어때요?? 강사님 강의에서도 요즘은 명확하게 하는게 트렌드라 하셨음.
 */
public enum SalesStatus {
    AVAILABLE,  // 판매중
    DISCONTINUED,  // 판매중지
    HIDDEN  // 숨기기
}
