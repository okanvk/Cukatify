package com.okanciftci.cukatify.persistence;

import com.okanciftci.cukatify.entities.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {

}
