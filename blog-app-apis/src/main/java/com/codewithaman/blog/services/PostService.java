package com.codewithaman.blog.services;

import java.util.List;


import com.codewithaman.blog.payloads.PostDto;
import com.codewithaman.blog.payloads.PostResponse;

public interface PostService {

	
	//Create
	PostDto createPost(PostDto postDto,Integer userId ,Integer categoryId);
	
	//update
	PostDto updatePost(PostDto postDto,Integer postId );
	
	//delete post 
	void deletePost(Integer postId);
	
	//get all post
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy);
	
	//single post
	
	PostDto  getPostById(Integer postId);
	
	//getPost by category
	List<PostDto> getPostByCategory(Integer categoryId);
	
	//Get all post by user
	List<PostDto> getPostByUser(Integer userId);
	
	//search post
	
	List<PostDto> searchPost(String keyword);
	 
}
