package com.codewithaman.blog.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.codewithaman.blog.entities.Category;
import com.codewithaman.blog.entities.Post;
import com.codewithaman.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	
	List<Post> findByCategory(Category category);
	List<Post> findByUser(User user);
	
	@Query("select p from Post p where p.title like :key")
	List<Post> searchBytitle(@Param("key") String title);
	

}
