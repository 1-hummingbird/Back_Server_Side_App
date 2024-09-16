package com.hummingbird.kr.starbuckslike.review.infrastructure.search;

import com.hummingbird.kr.starbuckslike.review.dto.out.QReviewListInfoResponseDto;
import com.hummingbird.kr.starbuckslike.review.dto.out.ReviewListImageResponseDto;
import com.hummingbird.kr.starbuckslike.review.dto.out.ReviewListInfoResponseDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.hummingbird.kr.starbuckslike.review.domain.QReview.review;
import static com.hummingbird.kr.starbuckslike.review.domain.QReviewImage.reviewImage;

@Repository
@RequiredArgsConstructor
@Log4j2
public class ReviewSearchImpl implements ReviewSearch{
    private final JPAQueryFactory queryFactory;
    @Override
    public Slice<Long> searchReviewListById(Pageable pageable, Long productId) {
        List<Long> fetch = queryFactory
                .select(review.id)
                .from(review)
                .where(
                        review.productId.eq(productId)
                        .and(review.isDelete.isFalse())
                )
                .orderBy(review.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        boolean hasNext = fetch.size() > pageable.getPageSize();

        if (hasNext) {
            fetch.remove(fetch.size() - 1);
        }
        return new SliceImpl<>(fetch, pageable, hasNext);
    }

    @Override
    public ReviewListImageResponseDto findReviewImageById(Long reviewId) {
        List<String> imageUrls = queryFactory
                .select(reviewImage.imageUrl)
                .from(reviewImage)
                .where(reviewImage.review.id.eq(reviewId))
                .orderBy(reviewImage.seq.asc())
                .fetch();
        return new ReviewListImageResponseDto(imageUrls);
    }

    @Override
    public ReviewListInfoResponseDto findReviewInfoById(Long reviewId) {
        return queryFactory
                .select(new QReviewListInfoResponseDto(review.nickname, review.star, review.createdAt, review.content))
                .from(review)
                .where(review.id.eq(reviewId))
                .fetchOne();
    }
}
