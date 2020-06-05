package com.okanciftci.cukatify.unit_integration;

import com.okanciftci.cukatify.entities.mongo.Category;
import com.okanciftci.cukatify.entities.mongo.Post;
import com.okanciftci.cukatify.models.mongo.CategoryModel;
import com.okanciftci.cukatify.models.mongo.PostModel;
import com.okanciftci.cukatify.services.abstr.CategoryService;
import com.okanciftci.cukatify.services.abstr.PostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private CategoryService categoryService;

    @Test
    void findPostGivenId(){

        // given
        String postId = "5e7930598596eb51e10572f2";
        String postCategoryName = "Rock";

        // when
        Post post = postService.findById(postId);

        // then
        Assertions.assertEquals(post.getRating(),3);
        Assertions.assertEquals(post.isApproved(),true);
        Assertions.assertEquals(post.getCategory().getName(),postCategoryName);

    }

    @Test
    void addPost() {

        Category category = categoryService.findCategoryById("5df787413b36bc3748631a89");
        PostModel postModel = new PostModel();
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setId(category.getId());


        postModel.setContent("post");
        postModel.setApproved(false);

        postModel.setCategoryModel(categoryModel);
        postModel.setTitle("title");
        postModel.setDescription("desc");

        postModel = postService.savePost(postModel);

        Post savedPost = postService.findById(postModel.getId());

        Assertions.assertEquals(savedPost.getTitle(),postModel.getTitle());
        Assertions.assertEquals(savedPost.getContent(),postModel.getContent());

    }
}
