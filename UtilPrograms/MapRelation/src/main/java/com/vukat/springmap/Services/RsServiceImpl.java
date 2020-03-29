package com.vukat.springmap.Services;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.vukat.springmap.Entities.*;
import com.vukat.springmap.Repository.*;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class RsServiceImpl implements RsService{

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    DumbPostRepository dumbPostRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public void createRatings(int type) {

        List<User> users = userRepository.findUsersByType(type);
        // 0 : pop
        // 1 : rock
        // 2 : classic

        List<Post> allPosts = postRepository.findAll();

        List<Post> popPosts = allPosts.stream().filter(post -> post.getCategory().getId().equals("5df787463b36bc3748631a8a")).collect(Collectors.toList());


        Collections.shuffle(popPosts);

        popPosts = popPosts.stream().limit(45).collect(Collectors.toList());

        List<Post> rockPosts = allPosts.stream().filter(post -> post.getCategory().getId().equals("5df787413b36bc3748631a89")).collect(Collectors.toList());


        Collections.shuffle(rockPosts);

        rockPosts = rockPosts.stream().limit(65).collect(Collectors.toList());

        List<Post> classicalPosts = allPosts.stream().filter(post -> post.getCategory().getId().equals("5df7874a3b36bc3748631a8b")).collect(Collectors.toList());


        Collections.shuffle(classicalPosts);

        classicalPosts = classicalPosts.stream().limit(65).collect(Collectors.toList());

        Random r = new Random();
        for(User u : users){

            for(Post pop : popPosts){

                Rating rating = createRating(pop,u);


                int low = 2;
                int high = 4;
                int result = r.nextInt(high-low) + low;
                rating.setRating(result);
                ratingRepository.save(rating);

            }

            for(Post rock : rockPosts){

                Rating rating = createRating(rock,u);

                int low = 2;
                int high = 5;
                int result = r.nextInt(high-low) + low;
                rating.setRating(result);
                ratingRepository.save(rating);

            }

            for(Post classic : classicalPosts){

                Rating rating = createRating(classic,u);

                int low = 4;
                int high = 6;
                int result = r.nextInt(high-low) + low;
                rating.setRating(result);

                ratingRepository.save(rating);

            }
        }




    }

    private Rating createRating(Post post,User u){
        Rating rating = new Rating();
        rating.setPostId(post.getId());
        rating.setUserId(u.getId());
        return rating;
    }





    @Override
    public void createRsByCategory(String id) {

        List<DumbPost> rockList = dumbPostRepository.findByCategoryId(id).stream().collect(Collectors.toList());

        Category category = categoryRepository.findById(id).get();

        List<Post> posts = new ArrayList<>();
        for(DumbPost dumbPost : rockList){

            Post post = new Post();

            post.setApproved(true);



            post.setContent(StringUtils.normalizeSpace(dumbPost.getContent()));

            post.setTitle(StringUtils.normalizeSpace(dumbPost.getTitle()));

            post.setDescription(StringUtils.normalizeSpace(dumbPost.getDescription()));

            post.setRating(0);

            post.setFileName(dumbPost.getFileName());

            post.addCategory(category);

            category.addPost(post);

            this.postRepository.save(post);

            this.categoryRepository.save(category);


        }
    }
}
