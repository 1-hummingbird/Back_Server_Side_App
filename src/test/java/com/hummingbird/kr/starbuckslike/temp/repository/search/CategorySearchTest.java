package com.hummingbird.kr.starbuckslike.temp.repository.search;

import com.hummingbird.kr.starbuckslike.temp.domain.Category;
import com.hummingbird.kr.starbuckslike.temp.dto.CategoryListDto;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
class CategorySearchTest {
    @Autowired
    private CategorySearch categorySearch;

    @Test
    void testFindCategoryByDepth(){
        Integer depth = 1;
        List<CategoryListDto> result = categorySearch.findCategoryByDepth(depth);

        result.forEach(category -> {
            log.info(
                    "Category id: " + category.getId() +
                            ", Name: " + category.getName() +
                            ", Path: " + category.getPath()
            );
        });
    }

}