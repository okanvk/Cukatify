package com.okanciftci.cukatify.persistence;

import com.okanciftci.cukatify.entities.Category;
import com.okanciftci.cukatify.entities.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Collection;
import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {

    @Query("{'category.id' : ?0}")
    Collection<Post> findByCategoryId(String id);

}
