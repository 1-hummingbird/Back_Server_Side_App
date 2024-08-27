package com.hummingbird.kr.starbuckslike.temp.repository;

import com.hummingbird.kr.starbuckslike.temp.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

}