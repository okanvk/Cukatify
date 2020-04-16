package com.okanciftci.cukatify.controllers;

import com.okanciftci.cukatify.common.ResponseEnum;
import com.okanciftci.cukatify.common.ResponsePayload;
import com.okanciftci.cukatify.common.service.impl.FileStorageServiceImpl;
import com.okanciftci.cukatify.entities.mongo.Post;
import com.okanciftci.cukatify.models.mongo.CategoryModel;
import com.okanciftci.cukatify.models.mongo.PostModel;
import com.okanciftci.cukatify.services.abstr.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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
public class PostController {


    @Autowired
    private PostService postService;

    @Autowired
    private FileStorageServiceImpl fileStorageService;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponsePayload bringPosts () {
            List<Post> posts = postService.takeAllPostsApproved();
            return new ResponsePayload(ResponseEnum.OK,posts);

    }

    @RequestMapping(value = "/findByCategoryId/{id}", method = RequestMethod.GET)
    public ResponsePayload bringPostsByCategoryId(@PathVariable String id) {

        List<Post> posts = postService.takeAllPostsByCategory(id);

        return new ResponsePayload(ResponseEnum.OK,posts);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    public ResponsePayload findPostById (@PathVariable String id) {
        Post post = postService.findById(id);
        return new ResponsePayload(ResponseEnum.OK,post);
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
            throw new RuntimeException();
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
    public ResponsePayload uploadFile(@RequestParam MultipartFile file,@RequestParam String title,@RequestParam String content,@RequestParam String description,@RequestParam boolean isApproved,@RequestParam String categoryId) {

        try{
            PostModel postModel = new PostModel();
            postModel.setTitle(title);
            postModel.setApproved(isApproved);
            postModel.setContent(content);
            postModel.setDescription(description);
            CategoryModel categoryModel = new CategoryModel();
            categoryModel.setId(categoryId);
            postModel.setCategoryModel(categoryModel);

            String fileName = fileStorageService.storeFile(file);
            postModel.setFileName(fileName);

            PostModel savedPost = postService.savePost(postModel);

            return new ResponsePayload(ResponseEnum.OK,savedPost);
        }catch (Exception e){
            return new ResponsePayload(ResponseEnum.BADREQUEST);
        }


    }





}
