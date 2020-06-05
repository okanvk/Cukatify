package com.okanciftci.cukatify.unit_integration;

import com.okanciftci.cukatify.entities.mongo.Category;

import com.okanciftci.cukatify.services.abstr.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CategoryServiceTest {


    @Autowired
    private CategoryService categoryService;


    @Test
    void findCategoryGivenId(){

        // given
        String categoryId = "5df787413b36bc3748631a89";

        // when
        Category category = categoryService.findCategoryById(categoryId);

        // then
        Assertions.assertEquals(category.getName(),"Rock");

    }

}

