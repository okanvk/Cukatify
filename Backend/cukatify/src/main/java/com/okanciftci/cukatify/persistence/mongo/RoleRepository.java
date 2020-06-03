package com.okanciftci.cukatify.persistence.mongo;

import com.okanciftci.cukatify.entities.mongo.Post;
import com.okanciftci.cukatify.entities.mongo.Role;
import com.okanciftci.cukatify.entities.mongo.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {

    Role findByName(String name);
}
