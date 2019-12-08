package com.okanciftci.cukatify.controllers;

import com.okanciftci.cukatify.common.ResponseEnum;
import com.okanciftci.cukatify.common.ResponsePayload;
import com.okanciftci.cukatify.entities.Post;
import com.okanciftci.cukatify.services.abstr.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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


}
