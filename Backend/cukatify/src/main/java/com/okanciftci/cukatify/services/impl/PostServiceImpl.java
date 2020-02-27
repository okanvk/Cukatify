package com.okanciftci.cukatify.services.impl;

import com.okanciftci.cukatify.common.ValidationMessage;
import com.okanciftci.cukatify.common.ValidationType;

import com.okanciftci.cukatify.entities.mongo.Category;
import com.okanciftci.cukatify.entities.mongo.Post;
import com.okanciftci.cukatify.models.mongo.PostModel;
import com.okanciftci.cukatify.persistence.mongo.CategoryRepository;
import com.okanciftci.cukatify.persistence.mongo.PostRepository;
import com.okanciftci.cukatify.services.abstr.CategoryService;
import com.okanciftci.cukatify.services.abstr.PostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @Override
    public List<Post> takeAllPostsApproved() {
        return this.postRepository.findByisApprovedIsTrue();
    }

    @Override
    public List<Post> takeAllPostsByCategory(String id) {
        return this.postRepository.findByCategoryId(id).stream().collect(Collectors.toList());
    }

    @Override
    public Post findById(String id) {
        return this.postRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public PostModel savePost(PostModel postModel) {

        Category category = categoryService.findCategoryById(postModel.getCategoryModel().getId());

        Post post = new Post();

        BeanUtils.copyProperties(postModel,post);

        post.addCategory(category);

        category.addPost(post);

        this.postRepository.save(post);

        this.categoryRepository.save(category);

        postModel.setId(post.getId());

        return postModel;
    }


    @Override
    public ValidationMessage validation( PostModel model, ValidationType validationType) {
        if (model == null) {
            return new ValidationMessage("Veri Gelmedi", "Kaydedilecek Veri Gelmedi", false);
        }
        switch (validationType) {
            case INSERT:
                if(model.getContent() == null || model.getContent().equals("") ) {
                    return new ValidationMessage("This field is necessary.", "Content must be filled", false);
                }
                else if(model.getTitle() == null || model.getTitle().equals("") ) {
                    return new ValidationMessage("This field is necessary.", "Title must be filled.", false);
                }
                else if(model.getDescription() == null || model.getDescription().equals("") ) {
                    return new ValidationMessage("This field is necessary.", "Description must be filled.", false);
                }
            default:
                break;
        }
        return new ValidationMessage("Valid", "Validation is okay", true);
    }
}
