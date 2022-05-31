package com.codewithaman.blog.repositories;

import com.codewithaman.blog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepo extends JpaRepository<Comment,Integer> {
}
