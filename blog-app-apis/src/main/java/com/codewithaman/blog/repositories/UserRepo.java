package com.codewithaman.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithaman.blog.entities.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}
