package com.vukat.springmap.Repository;

import com.vukat.springmap.Entities.Category;
import com.vukat.springmap.Entities.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {

    @Query("{ 'type': ?0 }")
    List<User> findUsersByType(int type);

}

