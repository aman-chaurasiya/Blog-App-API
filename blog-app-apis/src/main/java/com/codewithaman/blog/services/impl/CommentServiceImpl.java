package com.codewithaman.blog.services.impl;

import com.codewithaman.blog.entities.Comment;
import com.codewithaman.blog.entities.Post;
import com.codewithaman.blog.exceptions.ResourceNotFoundException;
import com.codewithaman.blog.payloads.CommentDto;
import com.codewithaman.blog.repositories.CommentsRepo;
import com.codewithaman.blog.repositories.PostRepo;
import com.codewithaman.blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentsRepo commentsRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId,Integer userID) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));

        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment = this.commentsRepo.save(comment);

        return this.modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = this.commentsRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment","commentId",commentId));
        this.commentsRepo.delete(comment);
    }

    @Override
    public List<CommentDto> getAllComment() {

        List<Comment> commentList = this.commentsRepo.findAll();
        List<CommentDto> commentDtoList = commentList.stream().map((comment) -> this.modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());

        return commentDtoList;
    }
}
