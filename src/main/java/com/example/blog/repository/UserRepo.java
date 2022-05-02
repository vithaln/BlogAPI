package com.example.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
