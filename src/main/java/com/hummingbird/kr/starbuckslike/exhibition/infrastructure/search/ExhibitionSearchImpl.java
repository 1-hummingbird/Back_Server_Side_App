package com.hummingbird.kr.starbuckslike.exhibition.infrastructure.search;

import com.hummingbird.kr.starbuckslike.exhibition.dto.ExhibitionDetailDto;
import com.hummingbird.kr.starbuckslike.exhibition.dto.ExhibitionListDto;
import com.hummingbird.kr.starbuckslike.exhibition.dto.QExhibitionDetailDto;
import com.hummingbird.kr.starbuckslike.exhibition.dto.QExhibitionListDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DatePath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static com.hummingbird.kr.starbuckslike.exhibition.domain.QExhibition.exhibition;


/**
 * 기획전 조회 구현부
 * @author 허정현
 */
@Repository
public class ExhibitionSearchImpl implements ExhibitionSearch {
    private final JPAQueryFactory queryFactory;
    public ExhibitionSearchImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<ExhibitionListDto> findAllExhibitionNames() {
        return queryFactory
                .select(new QExhibitionListDto(exhibition.id, exhibition.name)) // 전시회 이름 선택
                .from(exhibition)
                // 현재 날짜가 시작일과 종료일 사이에 있는지 확인
                .where(isCurrentDateBetween(exhibition.startDate, exhibition.endDate))
                .orderBy(exhibition.id.asc()) // 이름을 기준으로 오름차순 정렬
                .fetch();
    }

    @Override
    public ExhibitionDetailDto findExhibitionDetail(Long id) {
        //return null;
        return queryFactory
                .select(new QExhibitionDetailDto(exhibition.fullDescription))
                .from(exhibition)
                .where(
                        exhibition.id.eq(id)
                        .and(exhibition.isDeleted.isFalse()) // isDeleted가 false인지 확인
                )
                .fetchOne();
    }


    /**
     * 기획전의 모든 검색조건
     */

    // 현재 날짜가 주어진 시작일과 종료일 사이에 있는지 확인하는 조건
    private BooleanExpression isCurrentDateBetween(DatePath<LocalDate> startDate, DatePath<LocalDate> endDate) {
        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusDays(90);  // 30일 뒤의 날짜
        return startDate.loe(currentDate).and(endDate.goe(currentDate));
    }
}