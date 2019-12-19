package com.okanciftci.cukatify.services.abstr;

import com.okanciftci.cukatify.entities.Post;

import java.util.List;

public interface PostService {

    List<Post> takeAllPosts();

    List<Post> takeAllPostsByCategory(String id);

}
