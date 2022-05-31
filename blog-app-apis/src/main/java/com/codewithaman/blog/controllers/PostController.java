package com.codewithaman.blog.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import com.codewithaman.blog.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codewithaman.blog.payloads.ApiResponse;
import com.codewithaman.blog.payloads.PostDto;
import com.codewithaman.blog.payloads.PostResponse;
import com.codewithaman.blog.services.PostService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private FileService fileService;
    @Value("&{project.image}")
    private String path;

    // create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto poatDto, @PathVariable Integer userId,
                                              @PathVariable Integer categoryId) {

        PostDto createPost = this.postService.createPost(poatDto, userId, categoryId);
        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);

    }

    // get BY category

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
        List<PostDto> posts = this.postService.getPostByCategory(categoryId);

        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    //get post by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
        List<PostDto> posts = this.postService.getPostByUser(userId);

        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    @GetMapping("/postId/{postId}/posts")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        PostDto postById = this.postService.getPostById(postId);


        return new ResponseEntity<PostDto>(postById, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy
    ) {
        PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy);

        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
    }

    @DeleteMapping("/postId/{postId}/posts")
    public ResponseEntity<ApiResponse> deletepost(@PathVariable Integer postId) {
        this.postService.deletePost(postId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleting successfull", true), HttpStatus.OK);
    }

    @PutMapping("/postId/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
        PostDto updatePost = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
    }

    //search post
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(
            @PathVariable("keywords") String keywords
    ) {
        List<PostDto> results = this.postService.searchPost(keywords);

        return new ResponseEntity<List<PostDto>>(results, HttpStatus.OK);
    }

    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,
                                                   @PathVariable("postId") Integer postId) throws IOException {
        PostDto postDto = this.postService.getPostById(postId);
        String fileName = this.fileService.uploadImage(path, image);

        postDto.setImageName(fileName);
        PostDto updatedPost = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);

    }

    @GetMapping(value = "post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response
    ) throws IOException {
        InputStream resourse=this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resourse,response.getOutputStream());
    }

}
