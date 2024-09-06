package com.hummingbird.kr.starbuckslike.delivery.infrastructure;

import com.hummingbird.kr.starbuckslike.delivery.domain.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery,Long>{

    List<Delivery> findByUserUuid(String uuid);

}

//동적쿼리 :