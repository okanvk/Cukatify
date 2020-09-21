package com.okanciftci.cukatify.persistence.mongo;

import com.okanciftci.cukatify.entities.mongo.Post;
import com.okanciftci.cukatify.entities.mongo.Rating;
import com.okanciftci.cukatify.entities.mongo.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Collection;
import java.util.List;

public interface RatingRepository extends MongoRepository<Rating, String> {

    @Query("{'userId' : ?0, 'postId' : ?1}")
    Rating findRatingUserAndPost(String user_id, String post_id);

    @Query("{'postId' : ?0}")
    List<Rating> findPostRatings(String post_id);

}
