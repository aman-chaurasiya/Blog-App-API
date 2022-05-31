package com.codewithaman.blog.services;

import com.codewithaman.blog.payloads.CommentDto;

import java.util.List;

public interface CommentService {

    //create comment
    CommentDto createComment(CommentDto commentDto,Integer postId, Integer userId);
    //delete comment
    void deleteComment(Integer commentId);
    //get all comment

    List<CommentDto> getAllComment();

}
