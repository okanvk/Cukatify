package com.okanciftci.cukatify.services;

import com.okanciftci.cukatify.entities.Post;
import com.okanciftci.cukatify.persistence.PostRepository;
import com.okanciftci.cukatify.services.abstr.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;


    @Override
    public List<Post> takeAllPosts() {
        return this.postRepository.findAll();
    }
}
