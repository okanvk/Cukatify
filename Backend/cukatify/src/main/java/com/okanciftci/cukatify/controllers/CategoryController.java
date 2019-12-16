package com.okanciftci.cukatify.controllers;

import com.okanciftci.cukatify.common.ResponseEnum;
import com.okanciftci.cukatify.common.ResponsePayload;
import com.okanciftci.cukatify.entities.Category;
import com.okanciftci.cukatify.entities.Post;
import com.okanciftci.cukatify.services.abstr.CategoryService;
import com.okanciftci.cukatify.services.abstr.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/cukatify/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponsePayload bringCategories () {
        List<Category> posts = categoryService.takeAllCategories();
        return new ResponsePayload(ResponseEnum.OK,posts);

    }


}