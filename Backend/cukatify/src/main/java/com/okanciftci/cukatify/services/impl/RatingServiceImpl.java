package com.okanciftci.cukatify.services.impl;

import com.okanciftci.cukatify.entities.mongo.Post;
import com.okanciftci.cukatify.entities.mongo.Rating;
import com.okanciftci.cukatify.entities.mongo.User;
import com.okanciftci.cukatify.persistence.mongo.PostRepository;
import com.okanciftci.cukatify.persistence.mongo.RatingRepository;
import com.okanciftci.cukatify.services.abstr.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserService userService;


    @Override
    public Rating findUserRating(String username, Post post) {
        User user = userService.getUser(username);

        Rating rating = ratingRepository.findRatingUserAndPost(user.getId().toString(),post.getId());

        return  rating;
    }

    @Override
    public boolean ratePostByUserId(String post_id, Float rating_value, String username) {

        User user = userService.getUser(username);

        String user_id = user.getId().toString();

        Rating rating = ratingRepository.findRatingUserAndPost(user_id,post_id);

        if(rating != null){
            rating.setRating(rating_value);

            ratingRepository.save(rating);
        }else{
            Rating newRating = new Rating();

            newRating.setRating(rating_value);

            newRating.setPostId(post_id);

            newRating.setUserId(user_id);

            ratingRepository.save(newRating);
        }


        return true;
    }
}
