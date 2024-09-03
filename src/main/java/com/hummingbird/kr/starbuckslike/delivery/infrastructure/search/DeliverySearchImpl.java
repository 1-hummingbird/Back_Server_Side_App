package com.hummingbird.kr.starbuckslike.delivery.infrastructure.search;

import com.hummingbird.kr.starbuckslike.delivery.domain.QDelivery;
import com.hummingbird.kr.starbuckslike.delivery.dto.DeliveryDto;
import com.hummingbird.kr.starbuckslike.delivery.dto.QDeliveryDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.hummingbird.kr.starbuckslike.delivery.domain.QDelivery.delivery;

@Repository
public class DeliverySearchImpl implements DeliverySearch{

    private final JPAQueryFactory queryFactory;

    public DeliverySearchImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<DeliveryDto> getDeliveryDtoListById(String userUid) {
        return queryFactory
                .select(new QDeliveryDto(delivery.id,delivery.alias,
                        delivery.address,delivery.phone,
                        delivery.userUid))
                .from(delivery)
                .where(delivery.userUid.eq(userUid))
                .fetch();
    }

}
