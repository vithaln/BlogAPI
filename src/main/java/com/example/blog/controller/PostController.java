package com.example.blog.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.blog.constants.AppConst;
import com.example.blog.payloads.ApiResponse;
import com.example.blog.payloads.PostDto;
import com.example.blog.payloads.PostResponse;
import com.example.blog.service.FileService;
import com.example.blog.service.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postservice;

	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	// create post

	@PostMapping("User/{userId}/Category/{categoryId}/posts")
	public ResponseEntity<PostDto> CreatePost(@RequestBody PostDto postdto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {

		PostDto createdpost = this.postservice.createPost(postdto, userId, categoryId);

		return new ResponseEntity<PostDto>(createdpost, HttpStatus.CREATED);

	}

	// update posts

	@PutMapping("update/{postId}")
	public ResponseEntity<PostDto> UpdatePosts(@RequestBody PostDto postDto, @PathVariable Integer postId) {

		PostDto updatePost = this.postservice.UpdatePost(postDto, postId);

		return ResponseEntity.ok(updatePost);

	}
	// get by user

	@GetMapping("user/{userId}/posts")
	public ResponseEntity<List<PostDto>> GetPostsByUser(@PathVariable Integer userId) {

		// List<PostDto> posts=this.postservice.GetPostByUSer(userId);

		return new ResponseEntity<List<PostDto>>(this.postservice.GetPostByUSer(userId), HttpStatus.OK);

	}

	// get by category

	@GetMapping("category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> GetPostsByCategory(@PathVariable Integer categoryId) {

		// List<PostDto> posts=this.postservice.GetPostByUSer(userId);

		return new ResponseEntity<List<PostDto>>(this.postservice.GetPostByCategory(categoryId), HttpStatus.OK);

	}

	// get All posts
	@GetMapping("all/posts")
//	public ResponseEntity<List<PostDto>> GetAllposts(
	public ResponseEntity<PostResponse> GetAllposts(
//			@RequestParam(value="pagenumber",defaultValue="0", required=false) Integer pagenuber,
			@RequestParam(value="pagenumber",defaultValue=AppConst.PAGE_NUMBER, required=false) Integer pagenuber,
			@RequestParam(value="pagesize",defaultValue=AppConst.PAGE_SIZE,required=false) Integer pagesize,
			@RequestParam(value="sortBy", defaultValue=AppConst.SORT_BY,required=false)String sortBy,
			@RequestParam(value="sortDir",defaultValue=AppConst.SORT_DIR,required=false)String sortDir
			
			) {
		
//		List<PostDto> getAllPosts = this.postservice.GetAllPosts(pagenuber,pagesize);
		  PostResponse postResponse = this.postservice.GetAllPosts(pagenuber,pagesize,sortBy,sortDir);
	//	return new ResponseEntity<List<PostDto>>(getAllPosts, HttpStatus.OK);
		 return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);

	}

	// get by post by Id

	@PostMapping("/posts/{postId}")
	public ResponseEntity<PostDto> GetPostById(@PathVariable Integer postId) {

		PostDto getPostById = this.postservice.GetPostById(postId);

		return new ResponseEntity<PostDto>(getPostById, HttpStatus.OK);

	}

	// delete posts by id

	@DeleteMapping("delete/{postId}")
	public ResponseEntity<ApiResponse> DeletePost(@PathVariable Integer postId) {

		this.postservice.DeletePost(postId);

		return new ResponseEntity<ApiResponse>(new ApiResponse("post is deleted successfully!!", true), HttpStatus.OK);
	}
	
	//searching.........
	
	@GetMapping("search/posts/{keyword}")
	public ResponseEntity<List<PostDto>> SearchByTitle(@PathVariable String keyword){
		List<PostDto> searchPosts = postservice.SearchPosts(keyword);
		
		
		return new ResponseEntity<List<PostDto>>(searchPosts,HttpStatus.OK);
		
		
	}
	
	//post/upload the  image
	
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadImage(@RequestParam("image") MultipartFile image,
			@PathVariable("postId") Integer postId) throws IOException{
		
		PostDto postDto = this.postservice.GetPostById(postId);
		String filename = fileService.uploadImage(path, image);
		
		
		
		postDto.setImageName(filename);
		PostDto updatePost = this.postservice.UpdatePost(postDto, postId);
		
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
		
	}
	
	//method to serve the image
	
	@GetMapping(value="post/image/{imageName}",produces=MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName,
			HttpServletResponse response) throws IOException {
		
		InputStream resource=this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
		
		
	}
}
