package com.okanciftci.cukatify.persistence.mongo;

import com.okanciftci.cukatify.entities.mongo.Post;
import com.okanciftci.cukatify.entities.mongo.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository  extends MongoRepository<User, String> {

    User findByUsername(String username);
    User getById(String id);


}
