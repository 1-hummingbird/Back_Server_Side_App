package com.hummingbird.kr.starbuckslike.temp.repository;

import com.hummingbird.kr.starbuckslike.temp.domain.Product;
import com.hummingbird.kr.starbuckslike.temp.repository.search.ProductSearch;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
public class ProductRepositoryTest {

    @Autowired
    private ProductSearch productSearch;

    @Test
    public void testFindProductsByCategoryOrParents() {

        String path = "1/3";
        List<Product> products = productSearch.findProductsByPath(path);

        // 결과 출력
        products.forEach(
                product -> log.info("Product Name: " + product.getName())
        );
    }
}