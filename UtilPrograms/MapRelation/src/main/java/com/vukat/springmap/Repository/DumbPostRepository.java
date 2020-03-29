package com.vukat.springmap.Repository;

import com.vukat.springmap.Entities.Category;
import com.vukat.springmap.Entities.DumbPost;
import com.vukat.springmap.Entities.Post;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface DumbPostRepository extends MongoRepository<DumbPost, String> {

    @Query("{'category' : ?0 }")
    Collection<DumbPost> findByCategoryId(String id);



    List<Post> findByApprovedIsTrue();

    List<Post> findByisApprovedIsTrue();


}
