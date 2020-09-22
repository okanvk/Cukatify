package com.okanciftci.cukatify.controllers;

import com.okanciftci.cukatify.common.ResponseEnum;
import com.okanciftci.cukatify.common.ResponsePayload;
import com.okanciftci.cukatify.common.service.impl.FileStorageServiceImpl;
import com.okanciftci.cukatify.entities.mongo.Post;
import com.okanciftci.cukatify.entities.mongo.Rating;
import com.okanciftci.cukatify.models.mongo.CategoryModel;
import com.okanciftci.cukatify.models.mongo.PostModel;
import com.okanciftci.cukatify.services.abstr.PostService;
import com.okanciftci.cukatify.services.abstr.RatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/posts")
@Slf4j
public class PostController {


    @Autowired
    private PostService postService;

    @Autowired
    private FileStorageServiceImpl fileStorageService;

    @Autowired
    private RatingService ratingService;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponsePayload bringPosts () {
            List<Post> posts = postService.takeAllPostsApproved();
            return new ResponsePayload(ResponseEnum.OK,posts);

    }

    @RequestMapping(value = "/findAllStatePosts", method = RequestMethod.GET)
    public ResponsePayload bringPostsAllState () {
        List<Post> posts = postService.takeAllPostsAllState();
        return new ResponsePayload(ResponseEnum.OK,posts);

    }

    @RequestMapping(value = "/findByCategoryId/{id}", method = RequestMethod.GET)
    public ResponsePayload bringPostsByCategoryId(@PathVariable String id) {

        List<Post> posts = postService.takeAllPostsByCategory(id);

        return new ResponsePayload(ResponseEnum.OK,posts);
    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    public ResponsePayload findPostById (@PathVariable String id,Principal principal) {
        try{
            String username = principal.getName();
            Post post = postService.findById(id);
            post = postService.findPostTotalRating(post);
            Rating rating = ratingService.findUserRating(username,post);
            if(rating != null)
                post.setRating(rating.getRating());
            else
                post.setRating(0.f);
            return new ResponsePayload(ResponseEnum.OK,post);

        }catch (Exception e) {
            return new ResponsePayload(ResponseEnum.BADREQUEST,null);
        }
    }

    @RequestMapping(value = "/rate/{rating}/{post_id}", method = RequestMethod.PATCH)
    public ResponsePayload ratePostById (@PathVariable String post_id,@PathVariable Float rating,Principal principal) {
        try {
            boolean isSuccess = ratingService.ratePostByUserId(post_id,rating,principal.getName());
            return new ResponsePayload(ResponseEnum.OK,isSuccess);
       }catch (Exception e){
            return new ResponsePayload(ResponseEnum.BADREQUEST,false);
        }
    }

    @RequestMapping(value = "/toggle/{id}",method = RequestMethod.PATCH)
    public ResponseEntity<?> toggleUser(@PathVariable String id){

        try{
            boolean state = postService.togglePost(id);
            return new ResponseEntity<Boolean>(state, HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/findImage/{id}", method = RequestMethod.GET)
    public ResponseEntity findPostImageById (@PathVariable String id, HttpServletRequest request) {
        Post post = postService.findById(id);

        Resource resource = fileStorageService.loadFileAsResource(post.getFileName());

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            return ResponseEntity.badRequest().body(resource);
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponsePayload save(@RequestParam MultipartFile file,@RequestParam String title,@RequestParam String content,@RequestParam String description,@RequestParam boolean isApproved,@RequestParam String categoryId) {

        try{
            PostModel postModel = new PostModel();
            postModel.setTitle(title);
            postModel.setApproved(isApproved);
            postModel.setContent(content);
            postModel.setDescription(description);
            CategoryModel categoryModel = new CategoryModel();
            categoryModel.setId(categoryId);
            postModel.setCategoryModel(categoryModel);

            PostModel savedPost = postService.savePost(postModel);

            String fileName = fileStorageService.storeFile(file,savedPost.getId());

            postModel.setFileName(fileName);



            return new ResponsePayload(ResponseEnum.OK,savedPost);
        }catch (Exception e){
            return new ResponsePayload(ResponseEnum.BADREQUEST);
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponsePayload update(@RequestParam MultipartFile file,@RequestParam String title,@RequestParam String content,@RequestParam String description,@RequestParam boolean isApproved,@RequestParam String id,@RequestParam String categoryId) {

        try{
            PostModel postModel = new PostModel();
            postModel.setTitle(title);
            postModel.setId(id);
            postModel.setRating(0);
            postModel.setApproved(isApproved);
            postModel.setContent(content);
            postModel.setDescription(description);
            CategoryModel categoryModel = new CategoryModel();
            categoryModel.setId(categoryId);
            postModel.setCategoryModel(categoryModel);

            postModel.setFileName(postModel.getId() + ".jpg");
            PostModel updatedPost = postService.updatePost(postModel);

            String fileName = fileStorageService.storeFile(file,updatedPost.getId());
            postModel.setFileName(fileName);

            return new ResponsePayload(ResponseEnum.OK,updatedPost);
        }catch (Exception e){
            return new ResponsePayload(ResponseEnum.BADREQUEST);
        }
    }
}
