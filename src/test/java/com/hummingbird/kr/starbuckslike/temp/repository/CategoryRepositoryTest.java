package com.hummingbird.kr.starbuckslike.temp.repository;

import com.hummingbird.kr.starbuckslike.category.domain.Category;
import com.hummingbird.kr.starbuckslike.category.infrastructure.CategoryRepository;
import com.hummingbird.kr.starbuckslike.product.infrastructure.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
@Log4j2
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Test
    public void testFindAllCategory() {
        // 전체 카테고리 계층을 가져옴
        List<Category> categories = categoryRepository.findAllCategory();

        // 가장 상위 부모부터 출력
        categories.forEach(category -> {
            log.info(
                    "Depth: " + category.getDepth() +
                            ", Name: " + category.getName() +
                            ", Path: " + category.getPath() +
                            ", Parent: " + (category.getParent() != null ? category.getParent().getName() : "null")
            );
        });
    }

    @Test
    public void testFindParentCategoryById() {
        Long parentCategory = 2L;
        // 전체 카테고리 계층을 가져옴
        List<Category> categories = categoryRepository.findAllCategoryById(parentCategory);

        // 가장 상위 부모부터 출력
        categories.forEach(category -> {
            log.info(
                    "Depth: " + category.getDepth() +
                            ", Name: " + category.getName() +
                            ", Path: " + category.getPath() +
                            ", Parent: " + (category.getParent() != null ? category.getParent().getName() : "null")
            );
        });
    }



}