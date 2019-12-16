package com.okanciftci.cukatify.services.impl;

import com.okanciftci.cukatify.entities.Category;
import com.okanciftci.cukatify.entities.Post;
import com.okanciftci.cukatify.persistence.CategoryRepository;
import com.okanciftci.cukatify.services.abstr.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<Category> takeAllCategories() {
        return categoryRepository.findAll();
    }
}
