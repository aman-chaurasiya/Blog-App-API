package com.codewithaman.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithaman.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	

}
