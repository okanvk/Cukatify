package com.okanciftci.cukatify.unit;

import com.okanciftci.cukatify.entities.Category;

import com.okanciftci.cukatify.persistence.CategoryRepository;
import com.okanciftci.cukatify.services.abstr.CategoryService;
import com.okanciftci.cukatify.services.impl.CategoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest

public class CategoryServiceTest {
/*

    @Autowired
    private CategoryService categoryService;

    @MockBean
    private CategoryRepository categoryRepository;



    @Test
    void findCategoryById(){

        // given

        String categoryId = "5df787413b36bc3748631a89";

        // when

        List<Category> category = categoryService.takeAllCategories();

        // then

        Assertions.assertEquals(category,"Rock");


    }
*/
}

