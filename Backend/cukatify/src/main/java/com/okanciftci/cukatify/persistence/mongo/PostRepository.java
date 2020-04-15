package com.okanciftci.cukatify.persistence.mongo;

import com.okanciftci.cukatify.entities.mongo.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Collection;
import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {

    @Query("{'category.id' : ?0, 'isApproved' : ?1}")
    Collection<Post> findByCategoryId(String id,boolean isApproved);

    List<Post> findByisApprovedIsTrue();

}
