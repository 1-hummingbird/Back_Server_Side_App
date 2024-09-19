package com.hummingbird.kr.starbuckslike.review.dto.out;

import com.hummingbird.kr.starbuckslike.review.vo.out.ReviewCommentResponseVo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewCommentResponseDto {

    private String nickname;
    private String content;
    private LocalDateTime createAt;

    public ReviewCommentResponseVo toVo(){
        return ReviewCommentResponseVo.builder()
                .nickname(nickname)
                .content(content)
                .createAt(createAt)
                .build();
    }
    @QueryProjection
    public ReviewCommentResponseDto(String nickname, String content, LocalDateTime createAt) {
        this.nickname = nickname;
        this.content = content;
        this.createAt = createAt;
    }

}
