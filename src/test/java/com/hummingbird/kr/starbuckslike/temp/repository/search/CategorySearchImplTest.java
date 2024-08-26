package com.hummingbird.kr.starbuckslike.temp.repository.search;

import com.hummingbird.kr.starbuckslike.temp.domain.Category;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
class CategorySearchImplTest {
    @Autowired
    private CategorySearch categorySearch;

    @Test
    void testFindCategoryByDepth(){
        Integer depth = 1;
        List<Category> categoryByDepth = categorySearch.findCategoryByDepth(depth);

        categoryByDepth.forEach(category -> {
            log.info(
                    "Depth: " + category.getDepth() +
                            ", Name: " + category.getName() +
                            ", Path: " + category.getPath()
            );
        });
    }

}