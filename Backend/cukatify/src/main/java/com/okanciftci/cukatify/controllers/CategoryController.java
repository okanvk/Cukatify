package com.okanciftci.cukatify.controllers;

import com.okanciftci.cukatify.common.ResponseEnum;
import com.okanciftci.cukatify.common.ResponsePayload;
import com.okanciftci.cukatify.entities.mongo.Category;
import com.okanciftci.cukatify.services.abstr.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;


    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponsePayload bringCategories () {
        List<Category> posts = categoryService.takeAllCategories();
        return new ResponsePayload(ResponseEnum.OK,posts);

    }

    @RequestMapping(value = "/findCategory/{id}", method = RequestMethod.GET)
    public ResponsePayload bringCategoryById (@PathVariable String id) {
        Category category = categoryService.findCategoryById(id);
        return new ResponsePayload(ResponseEnum.OK,category);

    }





}