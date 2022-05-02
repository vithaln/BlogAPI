package com.example.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.blog.entities.Post;

public interface PostRepo extends JpaRepository<Post,Integer>{
	
//	List<Post> FindByUser(User user);
	
	//List<Post> FindByCategory(Category category);
	/**
	@Query("select p from post p where p.title like :key")
	List<Post> FindByTitleContaining(@Param("key") String title);
*/
}
