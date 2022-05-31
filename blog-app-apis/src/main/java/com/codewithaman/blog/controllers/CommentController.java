package com.codewithaman.blog.controllers;

import com.codewithaman.blog.entities.Comment;
import com.codewithaman.blog.payloads.ApiResponse;
import com.codewithaman.blog.payloads.CommentDto;

import com.codewithaman.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("post/{postId}/user/{userId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment, @PathVariable("postId") Integer postId,
                                                    @PathVariable("userId") Integer userId) {

        CommentDto commentDto = this.commentService.createComment(comment, postId,userId);


        return new ResponseEntity<CommentDto>(commentDto , HttpStatus.OK);
    }

    @DeleteMapping("comment/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable("commentId") Integer commentId) {

        this.commentService.deleteComment(commentId);


        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Successfully !!", true), HttpStatus.OK);
    }
    @GetMapping("comments")
    public ResponseEntity<List<CommentDto> >getAllComment() {

        List<CommentDto> allComment = this.commentService.getAllComment();


        return new ResponseEntity<List<CommentDto>>(allComment , HttpStatus.OK);
    }

}
