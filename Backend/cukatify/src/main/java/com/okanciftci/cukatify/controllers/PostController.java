package com.okanciftci.cukatify.controllers;

import com.okanciftci.cukatify.common.ResponseEnum;
import com.okanciftci.cukatify.common.ResponsePayload;
import com.okanciftci.cukatify.entities.Post;
import com.okanciftci.cukatify.services.abstr.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/cukatify/post")
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponsePayload bringPosts () {
            List<Post> posts = postService.takeAllPosts();
            return new ResponsePayload(ResponseEnum.OK,posts);

    }

    @RequestMapping(value = "/findByCategoryId/{id}", method = RequestMethod.GET)
    public ResponsePayload bringPostsByCategoryId(@PathVariable String id) {

        List<Post> posts = postService.takeAllPostsByCategory(id);

        return new ResponsePayload(ResponseEnum.OK,posts);
    }




}
