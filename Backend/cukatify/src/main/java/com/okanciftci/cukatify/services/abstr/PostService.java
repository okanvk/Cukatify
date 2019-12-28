package com.okanciftci.cukatify.services.abstr;

import com.okanciftci.cukatify.entities.mongo.Post;

import java.util.List;

public interface PostService {

    List<Post> takeAllPostsApproved();

    List<Post> takeAllPostsByCategory(String id);

    Post findById(String id);

}
