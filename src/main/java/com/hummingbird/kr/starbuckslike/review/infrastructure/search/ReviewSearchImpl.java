package com.hummingbird.kr.starbuckslike.review.infrastructure.search;

import com.hummingbird.kr.starbuckslike.batch.entity.QReviewStar;
import com.hummingbird.kr.starbuckslike.product.infrastructure.condition.PriceType;
import com.hummingbird.kr.starbuckslike.review.domain.QReviewComment;
import com.hummingbird.kr.starbuckslike.review.dto.out.*;
import com.hummingbird.kr.starbuckslike.review.infrastructure.condition.ReviewCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.hummingbird.kr.starbuckslike.batch.entity.QReviewStar.reviewStar;
import static com.hummingbird.kr.starbuckslike.product.domain.QProduct.product;
import static com.hummingbird.kr.starbuckslike.review.domain.QReview.review;
import static com.hummingbird.kr.starbuckslike.review.domain.QReviewComment.reviewComment;
import static com.hummingbird.kr.starbuckslike.review.domain.QReviewImage.reviewImage;

@Repository
@RequiredArgsConstructor
@Log4j2
public class ReviewSearchImpl implements ReviewSearch{
    private final JPAQueryFactory queryFactory;
    @Override
    public Slice<Long> searchReviewListById(Pageable pageable, Long productId , ReviewCondition reviewCondition) {
        List<Long> fetch = queryFactory
                .select(review.id)
                .from(review)
                .where(
                        review.productId.eq(productId)
                        .and(review.isDeleted.isFalse()),
                        photoReviewCondition(reviewCondition) // 포토 리뷰만 보기
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
                .select(new QReviewListInfoResponseDto(review.nickname, review.star,
                            review.createdAt, review.content, review.commentCount))
                .from(review)
                .where(review.id.eq(reviewId))
                .fetchOne();
    }

    @Override
    public Slice<Long> searchReviewListByMemberUuid(Pageable pageable, String memberUID) {
        List<Long> fetch = queryFactory
                .select(review.id)
                .from(review)
                .where(
                        review.memberUID.eq(memberUID)
                        .and(review.isDeleted.isFalse())
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
    public Boolean exists(String memberUuid, String purchaseCode, Long optionId) {
        Integer fetchOne = queryFactory
                            .selectOne()
                            .from(review)
                            .where(
                                    review.memberUID.eq(memberUuid)
                                    .and(review.purchaseCode.eq(purchaseCode))
                                    .and(review.optionId.eq(optionId))
                                    .and(review.isDeleted.isFalse())
                            )
                            .fetchFirst();
        return fetchOne != null;
    }

    @Override
    public List<ReviewCommentResponseDto> findReviewCommentById(Long reviewId) {
        long startTime = System.currentTimeMillis();

        List<ReviewCommentResponseDto> res = queryFactory
                .select(new QReviewCommentResponseDto(reviewComment.nickname,
                                                        reviewComment.content,
                                                        reviewComment.createdAt)
                )
                .from(reviewComment)
                .where(reviewComment.reviewId.eq(reviewId))
                .fetch();
        // 리뷰 댓글 특성상 데이터가 많지 않을 것으로 예상, 성능을 고려해 애플리케이션 단에서 정렬하도록 함
//        return res.stream()
//                .sorted(Comparator.comparing(ReviewCommentResponseDto::getCreateAt).reversed()) // 작성일 내림차순 정렬
//                .collect(Collectors.toList());
        List<ReviewCommentResponseDto> sortedRes = res.stream()
                .sorted(Comparator.comparing(ReviewCommentResponseDto::getCreateAt).reversed()) // 작성일 내림차순 정렬
                .toList();
        long endTime = System.currentTimeMillis();

        // 걸린 시간 출력
        long elapsedTime = endTime - startTime;
        log.info(" Review Comment(stream sort) Execution time: " + elapsedTime + " ms");

        return sortedRes;
    }

    @Override
    public List<ReviewCommentResponseDto> findReviewCommentByIdTest(Long reviewId) {
        long startTime = System.currentTimeMillis();

        List<ReviewCommentResponseDto> res = queryFactory
                .select(new QReviewCommentResponseDto(reviewComment.nickname,
                        reviewComment.content,
                        reviewComment.createdAt)
                )
                .from(reviewComment)
                .where(reviewComment.reviewId.eq(reviewId))
                .orderBy(reviewComment.createdAt.desc())
                .fetch();

        long endTime = System.currentTimeMillis();

        // 걸린 시간 출력
        long elapsedTime = endTime - startTime;
        log.info(" Review Comment(Order by sort) Execution time: " + elapsedTime + " ms");

        return res;
    }

    @Override
    public ReviewSummaryResponseDto findReviewSummaryDtoById(Long productId) {
        ReviewSummaryResponseDto dto = queryFactory
                .select(new QReviewSummaryResponseDto(
                        reviewStar.reviewCount,
                        reviewStar.photoReviewCount,
                        reviewStar.averageStar.floatValue()
                ))
                .from(reviewStar)
                .where(reviewStar.productId.eq(productId))
                .fetchFirst();
        if (dto == null) {
            dto = new ReviewSummaryResponseDto(0L, 0L, 0.0f);
        }
        return dto;
    }


    /**
     * 리뷰 조회의 모든 검색조건
     */
    private BooleanExpression photoReviewCondition(ReviewCondition reviewCondition) {
        log.info("reviewCondition :" + reviewCondition.getShowPhoto());
        if (reviewCondition.getShowPhoto() == null || !reviewCondition.getShowPhoto()) {
            return null;
        }
        return review.isPhoto.isTrue();
    }

}
