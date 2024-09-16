package com.hummingbird.kr.starbuckslike.review.dto.out;

import com.hummingbird.kr.starbuckslike.review.vo.out.ReviewListInfoResponseVo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewListInfoResponseDto {
    private String nickName;

    private Integer star;

    private LocalDateTime createAt;

    private String content;

    public ReviewListInfoResponseVo toVo(){
        return ReviewListInfoResponseVo.builder()
                .nickName(nickName)
                .star(star)
                .createAt(createAt)
                .content(content)
                .build();
    }

    @QueryProjection
    public ReviewListInfoResponseDto(String nickName, Integer star, LocalDateTime createAt, String content) {
        this.nickName = nickName;
        this.star = star;
        this.createAt = createAt;
        this.content = content;
    }
}
