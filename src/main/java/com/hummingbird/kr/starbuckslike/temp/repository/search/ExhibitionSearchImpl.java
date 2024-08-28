package com.hummingbird.kr.starbuckslike.temp.repository.search;

import com.hummingbird.kr.starbuckslike.temp.dto.ProductListDto;
import com.hummingbird.kr.starbuckslike.temp.dto.QProductListDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.hummingbird.kr.starbuckslike.temp.domain.QExhibitionProduct.exhibitionProduct;
import static com.hummingbird.kr.starbuckslike.temp.domain.QProduct.product;

/**
 * 기획전 조회 구현부
 * @author 허정현
 */
@Repository
public class ExhibitionSearchImpl implements  ExhibitionSearch{
    private final JPAQueryFactory queryFactory;
    public ExhibitionSearchImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ProductListDto> findProductListById(Long exhibitionId) {
        return queryFactory
                .selectDistinct(new QProductListDto(product.id, product.name, product.price))
                .from(product)
                //.join(product, exhibitionProduct.product) // 상품id 와 기획전상품의 상품 id 조인
                .join(exhibitionProduct)
                .on(product.id.eq(exhibitionProduct.product.id)) // 상품id 와 기획전상품의 상품 id 조인
                .where(exhibitionProduct.exhibition.id.eq(exhibitionId)) // exhibitionId에 해당하는 상품만 조회
                .fetch();


    }
}
