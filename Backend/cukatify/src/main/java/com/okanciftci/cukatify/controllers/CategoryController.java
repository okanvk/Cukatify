package com.okanciftci.cukatify.controllers;

import com.okanciftci.cukatify.common.ResponseEnum;
import com.okanciftci.cukatify.common.ResponsePayload;
import com.okanciftci.cukatify.entities.mongo.Category;
import com.okanciftci.cukatify.entities.ontology.Actor;
import com.okanciftci.cukatify.persistence.ontology.ActorRepository;
import com.okanciftci.cukatify.services.abstr.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {


    @Autowired
    private ActorRepository actorRepository;

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

    @RequestMapping(value = "/saveData",method = RequestMethod.POST)
    public ResponsePayload saveData(@RequestBody Actor actor){
        actorRepository.save(actor);
        return new ResponsePayload(ResponseEnum.OK);
    }

    @RequestMapping(value = "/takeData/{id}",method = RequestMethod.POST)
    public ResponsePayload takeData(@PathVariable String id){
        return new ResponsePayload(ResponseEnum.OK,actorRepository.findById(id));
    }




}