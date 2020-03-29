package com.vukat.springmap.Repository;

import com.vukat.springmap.Entities.Post;
import com.vukat.springmap.Entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RatingRepository extends MongoRepository<Rating, String> {
}
