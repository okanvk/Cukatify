package com.okanciftci.cukatify.services.abstr;

import com.okanciftci.cukatify.entities.Category;
import com.okanciftci.cukatify.entities.Post;

import java.util.List;

public interface CategoryService {

    List<Category> takeAllCategories();
}
