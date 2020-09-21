package com.okanciftci.cukatify.services.abstr;

import com.okanciftci.cukatify.entities.mongo.Post;
import com.okanciftci.cukatify.entities.mongo.Rating;

public interface RatingService {

    public Rating findUserRating(String name, Post post);

    public boolean ratePostByUserId(String post_id,Float rating,String username);
}
