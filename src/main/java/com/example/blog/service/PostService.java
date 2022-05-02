package com.example.blog.service;

import java.util.List;

import com.example.blog.entities.Post;
import com.example.blog.payloads.PostDto;
import com.example.blog.payloads.PostResponse;

public interface PostService {

	// create post

	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

	// update post

	PostDto UpdatePost(PostDto postDto, Integer postId);

	// Delete post

	void DeletePost(Integer postId);

	// Get All Post

	//List<PostDto> GetAllPosts();
	
//pagination concept......
//	List<PostDto> GetAllPosts(Integer pagenumber,Integer pagesize);
	
PostResponse GetAllPosts(Integer pagenumber,Integer pagesize,String sortBy,String sortDir);
	
	// get post By Id

	PostDto GetPostById(Integer postId);

	// get posts by User

	List<PostDto> GetPostByUSer(Integer userId);

	// Get posts by Category

	List<PostDto> GetPostByCategory(Integer categoryId);

	// searching post

	List<PostDto> SearchPosts(String keyword);

	/////////

}
